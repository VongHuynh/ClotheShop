package com.service;

import com.DTO.ProductStyleDTO;


import java.util.List;

public interface ProductStyleService {
    List<ProductStyleDTO> findAll();
    ProductStyleDTO findById(Integer id);
    ProductStyleDTO create(ProductStyleDTO productStyleDto);
    ProductStyleDTO update(ProductStyleDTO productStyleDto);
    void remove(Integer id);
    List<ProductStyleDTO> findAllByIdCategory(Integer idCategory);
}
