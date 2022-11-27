package com.serviceImpl;

import com.DAO.ProductDAO;
import com.DTO.ProductDTO;
import com.entity.Product;
import com.service.ProductService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;
    private final Convert convert;

    @Override //findAllAnd Pagination
    public Map<String,Object> findAll(Integer page,Integer size) {
        Map<String,Object> products = new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> listProduct = productDAO.findAll(pageable);
        List<ProductDTO> listProductDTO = listProduct.stream().
                map(product -> convert.toDto(product, ProductDTO.class))
                .collect(Collectors.toList());
        products.put("products",listProductDTO);
        products.put("totalPages",listProduct.getTotalPages());
        products.put("currentPage",listProduct.getNumber());
        products.put("first",listProduct.isFirst());
        products.put("last",listProduct.isLast());
        return products;
    }

    @Override
    public ProductDTO findById(Integer id) {
        Product product = productDAO.findById(id).get();
        return convert.toDto(product, ProductDTO.class);
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductDTO create(ProductDTO ProductDto) {
        Product newProduct = convert.toEntity(ProductDto, Product.class);
        newProduct.setCreateDate(new Date());
        ProductDTO productDto = convert.toDto(productDAO.save(newProduct), ProductDTO.class);
        return productDto;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public ProductDTO update(ProductDTO ProductDto) {
        Product newProduct = convert.toEntity(ProductDto,Product.class);
        newProduct.setCreateDate(new Date());
        ProductDTO productDto = convert.toDto(productDAO.save(newProduct), ProductDTO.class);
        return productDto;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(Integer id) {
        productDAO.deleteById(id);
    }

    @Override
    public List<ProductDTO> findAllByIdCategory(Integer idCategory) {
        List<Product> products = productDAO.findAllByIdCategory(idCategory);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> convert.toDto(product,ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOS;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
	public List<ProductDTO> page(int size, int page) {
    	Pageable pageable = PageRequest.of(page, size);
    	Page<Product> pages = productDAO.findAll(pageable);
    	return pages.stream().map(t -> convert.toDto(t, ProductDTO.class)).collect(Collectors.toList());
	}

    @Override
    public List<ProductDTO> fillByIdCategoryAndIdStyle(Integer idCategory,Integer idProductStyle){
        List<Product> products = productDAO.findByIdCategoryAndIdProductStyle(idCategory,idProductStyle);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> convert.toDto(product,ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findAllByNameProduct(String nameProduct){
        List<Product> products = productDAO.findAllByNameProductLike("%"+nameProduct+"%");
        List<com.DTO.ProductDTO> productDTOS = products.stream()
                .map(product -> convert.toDto(product, com.DTO.ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOS;
    }

}
