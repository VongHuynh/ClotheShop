package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProductStyleDTO implements Serializable {
    private Integer idProductStyle;
    private String nameProductStyle;
}
