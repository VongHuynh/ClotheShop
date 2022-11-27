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
@Table(name = "color")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColor;
    @Column(nullable = false,columnDefinition = "nvarchar(30)")
    private String nameColor;

    @OneToMany(mappedBy = "colorProduct",fetch = FetchType.LAZY)
    List<ProductColors> productColors;
}
