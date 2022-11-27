package com.serviceImpl;

import com.DAO.ColorDAO;
import com.DTO.ColorDTO;
import com.entity.Color;
import com.service.ColorService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorDAO colorDao;
    private final Convert convert;
    @Override
    public List<ColorDTO> findAll() {
        List<Color> colors = colorDao.findAll();
        List<ColorDTO> colorDTOS = colors.stream().map(color -> convert.toDto(color, ColorDTO.class))
                .collect(Collectors.toList());
        return colorDTOS;
    }

    @Override
    public ColorDTO findById(Integer id) {
        return convert.toDto(colorDao.findById(id), ColorDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ColorDTO create(ColorDTO ColorDto) {
        return convert.toDto(colorDao.save(convert.toEntity(ColorDto, Color.class)), ColorDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ColorDTO update(ColorDTO ColorDto) {
        return convert.toDto(colorDao.save(convert.toEntity(ColorDto, Color.class)), ColorDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(List<Integer> id) {
        colorDao.deleteAllById(id);
    }
}
