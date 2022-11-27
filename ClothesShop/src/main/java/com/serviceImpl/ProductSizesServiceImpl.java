package com.serviceImpl;

import com.DAO.ProductSizesDAO;
import com.DTO.ProductSizesDTO;
import com.DTO.SizeDTO;
import com.entity.ProductSizes;
import com.entity.Size;
import com.service.ProductSizesService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProductSizesServiceImpl implements ProductSizesService {
    private final ProductSizesDAO productSizesDAO;
    private final Convert convert;

    @Override
    public List<ProductSizesDTO> findAll() {
        List<ProductSizes> productSizes = productSizesDAO.findAll();
        List<ProductSizesDTO> productSizesDTOS = productSizes.stream()
                .map(productSize -> convert.toDto(productSize,ProductSizesDTO.class))
                .collect(Collectors.toList());
        return productSizesDTOS;
    }

    @Override
    public ProductSizesDTO findById(Integer id) {
        return null;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductSizesDTO create(ProductSizesDTO productSizesDto) {
        ProductSizes productSizes = convert.toEntity(productSizesDto,ProductSizes.class);
        ProductSizesDTO productSizesDTO = convert.toDto(productSizesDAO.save(productSizes),ProductSizesDTO.class);
        return productSizesDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductSizesDTO update(ProductSizesDTO productSizesDto) {
        ProductSizes productSizes = convert.toEntity(productSizesDto,ProductSizes.class);
        ProductSizesDTO productSizesDTO = convert.toDto(productSizesDAO.save(productSizes),ProductSizesDTO.class);
        return productSizesDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(Integer id) {
        productSizesDAO.deleteById(id);
    }

    @Override
    public ProductSizesDTO findByIdProductColorsAndIdSize(Integer idSize, Integer idProductColor){
        ProductSizes productSizes = productSizesDAO.findByIdProductColorsAndIdSize(idSize,idProductColor);
        ProductSizesDTO productSizesDTO = convert.toDto(productSizes,ProductSizesDTO.class);
        return productSizesDTO;
    }

    @Override
    public List<SizeDTO> findByIdProductColor(Integer idProductColor){
        List<Size> sizes = productSizesDAO.findByIdProductColor(idProductColor);
        List<SizeDTO>  sizeDTOS= sizes.stream()
                .map(size -> convert.toDto(size,SizeDTO.class))
                .collect(Collectors.toList());
        return sizeDTOS;
    }
//    @Override
//    public List<ProductDTO> findByIdSize(Integer idSize){
//        List<Product> products = productSizesDAO.findByIdSize(idSize);
//        List<ProductDTO> ProductDTOS = products.stream()
//                .map(product -> convert.toDto(product,ProductDTO.class))
//                .collect(Collectors.toList());
//        return  ProductDTOS;
//
//    }
//
//    @Override
//    public List<ProductSizesDTO> findByIdProduct(Integer idProduct) {
//         List<ProductSizes> productSizes = productSizesDAO.findByIdProduct(idProduct);
//         List<ProductSizesDTO> productSizesDTOS = productSizes.stream()
//                 .map(productSize -> convert.toDto(productSize,ProductSizesDTO.class))
//                 .collect(Collectors.toList());
//        return productSizesDTOS;
//    }
}
