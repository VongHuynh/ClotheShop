package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class FavoriteDTO implements Serializable {
    private Integer idProduct;
    private String  idUsernameAccount;
}
