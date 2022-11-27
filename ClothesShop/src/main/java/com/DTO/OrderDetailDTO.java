package com.DTO;

import com.entity.Orders;
import com.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderDetailDTO implements Serializable {
    private int quantity;
    private double amount;
    private String colorName;
    private String sizeName;
    private String nameProduct;
    private Integer idProduct;
    private Integer ordersId;
    private Integer idDetail;
}
