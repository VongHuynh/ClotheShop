package com.DTO;

import com.entity.ProductStyle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Integer idCategory;
    private String nameCategory;
    private List<ProductStyleDTO> productStyles;
}
