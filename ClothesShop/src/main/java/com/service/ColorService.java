package com.service;

import com.DTO.ColorDTO;

import java.util.List;

public interface ColorService {
    List<ColorDTO> findAll();
    ColorDTO findById(Integer id);
    ColorDTO create(ColorDTO ColorDto);
    ColorDTO update(ColorDTO ColorDto);
    void remove(List<Integer> id);
}
