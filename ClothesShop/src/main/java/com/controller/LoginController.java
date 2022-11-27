package com.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.entity.Account;
import com.entity.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.LoginRequest;
import com.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginRequest loginRequest){
        Map<String,String> errors = new HashMap<>();
        Account account = accountService.existsAccount(loginRequest);
        if( account == null){
            errors.put("username","Tài khoản không tồn tại");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }else if(!passwordEncoder.matches(loginRequest.getPassword(),passwordEncoder.encode(account.getPassword()))){
            errors.put("password","Mật khẩu không chính xác");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);//xác minh người dùng
        User user = (User) authentication.getPrincipal();// lấy user ra sau khi xác minh thành công
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        // tạo access token
        String access_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        //tạo refresh token
        String refresh_token = JWT.create().withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .sign(algorithm);

        List<String> roles = user.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        return ResponseEntity.ok(new LoginResponse(user.getUsername(),true,tokens,roles));
    }


    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
            //lấy header authorization
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){
                try{
                    String refresh_token = authorizationHeader.substring("Bearer ".length());// lấy refresh token
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();

                    DecodedJWT decodedJWT = verifier.verify(refresh_token);

                    String username = decodedJWT.getSubject();
                    Account user = accountService.getUser(username);

                    // tạo access token
                    String access_token = JWT.create().withSubject(user.getIdUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles",user.getAuthorities().stream().map(role -> role.getRole().getIdRole()).collect(Collectors.toList()))
                            .sign(algorithm);

                    Map<String,String> tokens = new HashMap<>();
                    tokens.put("access_token",access_token);
                    tokens.put("refresh_token",refresh_token);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),tokens);//trả về tokens cho người dùng
                }catch(Exception e){
                    response.setStatus(FORBIDDEN.value());
                    //response.sendError(FORBIDDEN.value());
                    Map<String,String> error = new HashMap<>();
                    error.put("error_message",e.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(),error);//trả về tokens cho người dùng
                }
            }else
                throw new RuntimeException();

    }
}

