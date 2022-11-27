package com.service;

import com.DTO.CategoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(Integer id);
    CategoryDTO create(CategoryDTO CategoryDto);
    CategoryDTO update(CategoryDTO CategoryDto);
    void remove(int id);
	void removeAll(List<Integer> id);
}
