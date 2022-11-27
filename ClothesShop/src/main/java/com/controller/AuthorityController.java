package com.controller;


import com.DTO.AuthoritiesDTO;
import com.service.AuthoritiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class AuthorityController {
    private final AuthoritiesService authoritiesService;

    @PostMapping("admin/authority")
    public ResponseEntity<AuthoritiesDTO> newAuthority(@RequestBody AuthoritiesDTO authoritiesDTO){

        return ResponseEntity.ok(authoritiesService.create(authoritiesDTO));
    }


}
