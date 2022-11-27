package com.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;

    /*--Quản lý người dữ liệu người sử dụng--*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override //phân quyền
    protected void configure(HttpSecurity http)
            throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/api/v1/login", "/api/v1/token/refresh").permitAll(); //nếu token hết hạn thì trả về refresh token ko cần đăng nhập lại
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/user/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority("user", "admin");
//        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAnyAuthority("user", "admin");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/v1/admin/**").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/admin/**").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/admin/**").hasAnyAuthority("admin","user");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/v1/admin/**").hasAnyAuthority("admin","user");

        http.formLogin().disable();
        http.httpBasic().disable();
        //xác minh vai trò của người dùng
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//    }

}
