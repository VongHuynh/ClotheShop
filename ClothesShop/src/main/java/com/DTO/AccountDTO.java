package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private String username;
    private String fullname;
    private String email;
    private String photo;
    private String phoneNumber;
}
