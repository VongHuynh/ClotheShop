package com.serviceImpl;

import com.DAO.SizeDAO;
import com.DTO.SizeDTO;
import com.entity.Size;
import com.service.SizeService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeDAO sizeDAO;
    private final Convert convert;
    @PersistenceContext
    private final EntityManager em;
    @Override
    public List<SizeDTO> findAll() {
        List <Size> sizes = sizeDAO.findAll();
        List <SizeDTO> sizeDTOs =  sizes.stream().map(size -> convert.toDto( size, SizeDTO.class))
                .collect(Collectors.toList());
        return sizeDTOs;
    }

    @Override
    public SizeDTO findById(Integer id) {
        return convert.toDto(sizeDAO.findById(id).get(),SizeDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public SizeDTO create(SizeDTO SizeDto) {
        return convert.toDto(sizeDAO.save(convert.toEntity(SizeDto, Size.class)), SizeDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public SizeDTO update(SizeDTO SizeDto) {
        return convert.toDto(sizeDAO.save(convert.toEntity(SizeDto, Size.class)), SizeDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(List<Integer> id) {
        sizeDAO.deleteAllById(id);
    }

}
