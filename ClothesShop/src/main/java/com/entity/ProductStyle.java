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
@Table(name = "productstyle")
public class ProductStyle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductStyle;
    @Column(nullable = false,columnDefinition = "nvarchar(50)")
    private String nameProductStyle;
    @OneToMany(mappedBy = "productStyle",fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne @JoinColumn(name = "idcategory")
    Category categoryType;
}
