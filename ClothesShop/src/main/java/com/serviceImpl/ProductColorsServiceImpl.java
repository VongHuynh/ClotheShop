package com.serviceImpl;

import com.DAO.ProductColorsDAO;
import com.DTO.ColorDTO;
import com.DTO.ProductColorsDTO;
import com.DTO.ProductDTO;
import com.entity.Color;
import com.entity.Product;
import com.entity.ProductColors;
import com.service.ProductColorsService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductColorsServiceImpl implements ProductColorsService {


    private final ProductColorsDAO productColorsDAO;

    private final Convert convert;

    @Override
    public Map<String,Object> findAll(int page,int size) {
        Map<String,Object> productColors = new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductColors> productColor = productColorsDAO.findAll(pageable);
        List<ProductColorsDTO> productColorsDTOS = productColor.stream()
                .map(product -> convert.toDto(product, ProductColorsDTO.class))
                .collect(Collectors.toList());
        productColors.put("products",productColorsDTOS);
        productColors.put("totalPages",productColor.getTotalPages());
        productColors.put("currentPage",productColor.getNumber());
        productColors.put("first",productColor.isFirst());
        productColors.put("last",productColor.isLast());
        return productColors;
    }

    @Override
    public ProductColorsDTO findById(Integer id) {
        return null;
    }

    @Override
    public ProductColorsDTO findByIdColorAndIdProduct(Integer idColor, Integer idProduct) {
        ProductColors productColors = productColorsDAO.findByIdColorAndIdProduct(idColor,idProduct);
        ProductColorsDTO productColorsDTO = convert.toDto(productColors, ProductColorsDTO.class);
        return productColorsDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductColorsDTO create(ProductColorsDTO productColorsDto) {
        ProductColors productColors = convert.toEntity(productColorsDto,ProductColors.class);
        ProductColorsDTO productColorsDTO = convert.toDto(productColorsDAO.save(productColors),ProductColorsDTO.class);
        return productColorsDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductColorsDTO update(ProductColorsDTO productColorsDto) {
        ProductColors productColors = convert.toEntity(productColorsDto,ProductColors.class);
        ProductColorsDTO productColorsDTO = convert.toDto(productColorsDAO.save(productColors),ProductColorsDTO.class);
        return productColorsDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(Integer id) {
        productColorsDAO.deleteById(id);
    }


    @Override
    public List<ColorDTO> findByIdProduct(Integer idProduct) {
        List<Color> colors = productColorsDAO.findByIdProduct(idProduct);
        List<ColorDTO> colorDTOS= colors.stream()
                .map(productColor -> convert.toDto(productColor, ColorDTO.class))
                .collect(Collectors.toList());
        return colorDTOS;
    }

    @Override
    public Map<String,Object> findByColor(Integer idColor,int page, int size) {
        Map<String,Object> productColors = new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductColors> productColor = productColorsDAO.fillByColor(idColor,pageable);
        List<ProductColorsDTO> productColorDTO = productColor.stream()
                .map(product -> convert.toDto(product, ProductColorsDTO.class))
                .collect(Collectors.toList());
        productColors.put("products",productColorDTO);
        productColors.put("totalPages",productColor.getTotalPages());
        productColors.put("currentPage",productColor.getNumber());
        return productColors;
    }

    @Override
    public Map<String,Object> fillBySize(Integer idSize,int page,int size){
        Map<String,Object> productColors = new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<ProductColors> productColor = productColorsDAO.fillBySize(idSize,pageable);
        List<ProductColorsDTO> productColorDTO = productColor.stream()
                .map(product -> convert.toDto(product, ProductColorsDTO.class))
                .collect(Collectors.toList());
        productColors.put("products",productColorDTO);
        productColors.put("totalPages",productColor.getTotalPages());
        productColors.put("currentPage",productColor.getNumber());
        return productColors;
    }
}
