package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;
    @Column(nullable = false,columnDefinition = "varchar(200)")
    private String nameProduct;
    @Column(nullable = false)
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Date createDate;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String productPhoto;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    List<ProductColors> productColors;

    @ManyToOne @JoinColumn(name = "idcategory")
    private Category category;

    @ManyToOne @JoinColumn(name = "idproductstyle")
    private ProductStyle productStyle;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<Favorite> favorites;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<Comment> comments;

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<OrderDetail> orderDetails;
}
