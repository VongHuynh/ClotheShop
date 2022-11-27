package com.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Integer idProduct;
    @NotBlank(message = "{NotBlank.ProductDTO.nameProduct}")
    private String nameProduct;
    @Min(value = 1, message = "{Min.ProductDTO.price}")
    private double price;
    @NotBlank(message = "{NotEmpty.ProductDTO.productPhoto}")
    private String productPhoto;
    private String description;
    @Min(value = 1, message = "{Min.ProductDTO.idProductStyle}")
    @NotNull
    private Integer idProductStyle;
    @Min(value = 1, message = "{Min.ProductDTO.idCategory}")
    @NotNull
    private Integer idCategory;
}
