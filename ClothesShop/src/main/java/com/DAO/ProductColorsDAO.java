package com.DAO;

import com.entity.Color;
import com.entity.Product;
import com.entity.ProductColors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductColorsDAO extends JpaRepository<ProductColors,Integer> {
    @Query("select  o from ProductColors o where o.colorProduct.idColor=:idColor and o.product.idProduct=:idProduct")
    ProductColors findByIdColorAndIdProduct(Integer idColor, Integer idProduct);

    @Query("select  o.colorProduct from ProductColors o where o.product.idProduct=?1")
    List<Color> findByIdProduct(Integer idProduct);

    @Query("select o from ProductColors o where o.colorProduct.idColor=?1")
    Page<ProductColors> fillByColor(Integer idColor, Pageable pageable);

    @Query("select o.productColors from ProductSizes  o where o.size.idSize=?1")
    Page<ProductColors> fillBySize(Integer idSize, Pageable pageable);
}
