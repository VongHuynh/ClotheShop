package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orderdetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetail;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double amount;
    @Column(nullable = false,columnDefinition = "varchar(30)")
    private String colorName;
    @Column(nullable = false,columnDefinition = "varchar(30)")
    private String sizeName;
    @ManyToOne @JoinColumn(name = "idproduct")
    private Product product;

    @ManyToOne @JoinColumn(name = "idorders")
    private Orders orders;


}
