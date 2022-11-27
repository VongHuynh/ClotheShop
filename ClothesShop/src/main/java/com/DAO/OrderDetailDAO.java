package com.DAO;

import com.entity.OrderDetail;
import com.entity.ReportBestSellingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderDetailDAO extends JpaRepository<OrderDetail,Integer> {
    @Query("select new ReportBestSellingProduct(o.product.idProduct,o.product.nameProduct," +
            "count(o.quantity),sum(o.amount),o.product.price) from OrderDetail o " +
            "where month(o.orders.createDate) >= :month and " +
            "year(o.orders.createDate) between :fromYear and :toYear")
    List<ReportBestSellingProduct> reportBestSellingOfProduct(@Param("fromYear") Integer fromYear,
                                                              @Param("toYear") Integer toYear,
                                                              @Param("month") Integer month);

    @Query("select o from OrderDetail o where o.orders.account.idUsername=?1")
    List<OrderDetail> getOrderDetailsByUser(String username);
}
