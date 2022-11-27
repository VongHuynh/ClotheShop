package com.serviceImpl;

import com.DAO.OrdersDAO;
import com.DTO.OrdersDTO;
import com.entity.Orders;
import com.service.OrdersService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersDAO ordersDAO;
    private final Convert convert;
    @Override
    public List<OrdersDTO> findAll() {
        return null;
    }

    @Override
    public OrdersDTO findById(Integer id) {
        return null;
    }

    @Override
    public OrdersDTO create(OrdersDTO OrdersDto) {
        Orders orders = convert.toEntity(OrdersDto,Orders.class);
        OrdersDTO ordersDTO = convert.toDto(ordersDAO.save(orders),OrdersDTO.class);
        return ordersDTO;
    }

    @Override
    public void update(OrdersDTO OrdersDto) {

    }

    @Override
    public void remove(Integer id) {

    }
}
