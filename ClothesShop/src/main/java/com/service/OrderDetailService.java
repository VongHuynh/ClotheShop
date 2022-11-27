package com.service;

import com.DTO.OrderDetailDTO;
import com.entity.ReportBestSellingProduct;

import java.util.Date;
import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> findAll();
    OrderDetailDTO findById(Integer id);
    OrderDetailDTO create(OrderDetailDTO OrderDetailDto);

    List<OrderDetailDTO> createAll(List<OrderDetailDTO> orderDetailDTO);

    List<OrderDetailDTO> findAllByUsername(String username);

    List<ReportBestSellingProduct> reportBestSellingProducts(Integer fromYear, Integer toYear, Integer month);

    void update(OrderDetailDTO OrderDetailDto);
    void remove(Integer id);
}
