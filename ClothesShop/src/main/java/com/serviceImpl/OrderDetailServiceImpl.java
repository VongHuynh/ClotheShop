package com.serviceImpl;

import com.DAO.OrderDetailDAO;
import com.DTO.OrderDetailDTO;
import com.entity.OrderDetail;
import com.entity.ReportBestSellingProduct;
import com.service.OrderDetailService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {


    private final OrderDetailDAO orderDetailDAO;


    private final Convert convert;
    @Override
    public List<OrderDetailDTO> findAll() {
        return null;
    }

    @Override
    public OrderDetailDTO findById(Integer id) {
        return null;
    }

    @Override
    public OrderDetailDTO create(OrderDetailDTO orderDetailDto) {
        OrderDetail orderDetail = convert.toEntity(orderDetailDto,OrderDetail.class);

        OrderDetailDTO orderDetailDTO = convert.toDto(orderDetailDAO.save(orderDetail),OrderDetailDTO.class);
        return orderDetailDTO;
    }

    @Override
    public List<OrderDetailDTO> createAll(List<OrderDetailDTO> orderDetailDTO){

        List<OrderDetail> orderDetails = orderDetailDTO.stream().map(orderDetail -> convert.toEntity(orderDetail,OrderDetail.class))
                .collect(Collectors.toList());


        List<OrderDetail> orderDetailsSaved = orderDetailDAO.saveAll(orderDetails);

        List<OrderDetailDTO> orderDetailDTOS = orderDetailsSaved.stream()
                .map(orderDetail -> convert.toDto(orderDetail,OrderDetailDTO.class))
                .collect(Collectors.toList());

        return orderDetailDTOS;
    }

    @Override
    public List<OrderDetailDTO> findAllByUsername(String username){
        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByUser(username);
        List<OrderDetailDTO> orderDetailDTOS = orderDetails.stream()
                .map(orderDetail -> convert.toDto(orderDetail,OrderDetailDTO.class))
                .collect(Collectors.toList());
        return orderDetailDTOS;
    }

    @Override
    public List<ReportBestSellingProduct> reportBestSellingProducts(Integer fromYear,
                                                                    Integer toYear,
                                                                    Integer month){
        return orderDetailDAO.reportBestSellingOfProduct(fromYear,toYear,month);
    }

    @Override
    public void update(OrderDetailDTO OrderDetailDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
