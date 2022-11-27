package com.DTO;

import com.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class ProductSizesDTO implements Serializable {
    @NotNull(message = "{NotNull.ProductSizesDTO.idSize}")
    private Integer idSize;
    private int quantity;
    private ColorDTO colorProduct;
    private ProductDTO product;
}
