package com.service;

import com.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAll();
    CommentDTO findById(Integer id);
    CommentDTO create(CommentDTO CommentDto);
    void update(CommentDTO CommentDto);
    void remove(Integer id);

    List<CommentDTO> findAllByIdProduct(Integer id);
}
