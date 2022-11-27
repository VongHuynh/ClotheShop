package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "{NotBlank.LoginRequest.username}")
    String username;
    @NotBlank(message = "{NotBlank.LoginRequest.password}")
    String password;
}
