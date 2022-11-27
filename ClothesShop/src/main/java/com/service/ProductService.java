package com.service;

import com.DTO.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Map<String,Object> findAll(Integer page, Integer size);
    ProductDTO findById(Integer id);
    ProductDTO create(ProductDTO ProductDto);
    ProductDTO update(ProductDTO ProductDto);
    void remove(Integer id);
    List<ProductDTO> findAllByIdCategory(Integer idCategory);
    List<ProductDTO> page(int size, int page);


    List<ProductDTO> fillByIdCategoryAndIdStyle(Integer idCategory, Integer idProductStyle);

    List<ProductDTO> findAllByNameProduct(String nameProduct);
}
