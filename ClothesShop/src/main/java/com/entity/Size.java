package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "size")
public class Size {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSize;
    @Column(nullable = false, length = 20)
    private String nameSize;

    @OneToMany(mappedBy = "size",fetch = FetchType.LAZY)
    List<ProductSizes> productSizess;
}
