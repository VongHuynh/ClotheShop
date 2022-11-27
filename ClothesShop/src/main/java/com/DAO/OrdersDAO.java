package com.DAO;

import com.entity.OrderDetail;
import com.entity.Orders;
import com.entity.ReportBestSellingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersDAO extends JpaRepository<Orders,Integer> {



}
