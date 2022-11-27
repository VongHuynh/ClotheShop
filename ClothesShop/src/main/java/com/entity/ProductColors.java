package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productcolors")
public class ProductColors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductsColors;
    @Column(nullable = false,columnDefinition = "TEXT")
    private String photoColor;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "idproduct")
    private Product product;
    @ManyToOne @JoinColumn(name = "idcolor")
    private Color colorProduct;

    @OneToMany(mappedBy = "productColors",fetch = FetchType.LAZY)
    List<ProductSizes> productSizes;
}
