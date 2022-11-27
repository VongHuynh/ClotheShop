package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class AuthoritiesDTO implements Serializable {
    private String roleId;
    private String accountIdUsername;
    private Long idAuthorities;
}
