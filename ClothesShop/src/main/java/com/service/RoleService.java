package com.service;

import com.DTO.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> findAll();
    RoleDTO findById(Integer id);
    void create(RoleDTO RoleDto);
    void update(RoleDTO RoleDto);
    void remove(Integer id);
}
