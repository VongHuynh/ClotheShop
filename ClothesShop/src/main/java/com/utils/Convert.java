package com.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;

@Configuration
@RequiredArgsConstructor
public class Convert {

    private final ModelMapper modelMapper;
    public <E,EDTO> EDTO toDto(E entity, Class<EDTO> DTOClass) {
        EDTO DTO = modelMapper.map(entity, DTOClass);
        return DTO;
    }

    public <E,EDTO> E toEntity(EDTO DTO, Class<E> EClass) {
        E entity = modelMapper.map(DTO,EClass);
        return entity;
    }
}
