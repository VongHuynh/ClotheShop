package com.service;

import com.DTO.SizeDTO;

import java.util.List;

public interface SizeService {
    List<SizeDTO> findAll();
    SizeDTO findById(Integer id);
    SizeDTO create(SizeDTO SizeDto);
    SizeDTO update(SizeDTO SizeDto);
    void remove(List<Integer> id);

}
