package com.DAO;

import com.entity.Product;
import com.entity.ProductColors;
import com.entity.ProductSizes;
import com.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizesDAO extends JpaRepository<ProductSizes,Integer> {
//    @Query("select  o from ProductSizes  o where o.product.idProduct =?1 and o.sizeProduct.idSize=?2")
//    ProductSizes findByIdProductAndIdSize(Integer idProduct, Integer idSize);
//
////    @Query("select  o from ProductSizes  o where o.product.idProduct =?1")
////    List<ProductSizes> findByIdProduct(Integer idProduct);
//
//    @Query("select  o.product from ProductSizes o where o.sizeProduct.idSize=?1")
//    List<Product> findByIdSize(Integer idSize);

    @Query("select o from ProductSizes o where o.size.idSize=?1 and o.productColors.idProductsColors=?2")
    ProductSizes findByIdProductColorsAndIdSize(Integer idSize, Integer idProductColor);
    @Query("select  o.size from ProductSizes o where o.productColors.idProductsColors=?1")
    List<Size> findByIdProductColor(Integer idProductColor);


}
