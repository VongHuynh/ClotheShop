package com.DTO;


import com.entity.ProductSizes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
public class ProductColorsDTO implements Serializable {
    @NotNull(message = "{NotNull.ProductColorsDTO.idColor}")
    private Integer idColor;
    private String nameColor;
    @NotBlank(message = "{NotBlank.ProductColorsDTO.photoColor}")
    private String photoColor;
    @NotNull(message = "{NotNull.ProductColorsDTO.idProduct}")
    private Integer idProduct;
    @NotBlank(message = "{NotBlank.ProductDTO.nameProduct}")
    private String nameProduct;
    @Min(value = 1, message = "{Min.ProductDTO.price}")
    private double priceProduct;
    private Integer idProductsColors;
    private String descriptionProduct;
}
