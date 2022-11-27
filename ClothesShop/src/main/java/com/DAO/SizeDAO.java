package com.DAO;

import com.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SizeDAO extends JpaRepository<Size,Integer> {
    @Query("select o from ProductSizes o where o.productColors.idProductsColors=?1")
    List<Size> findSizeByIdProductColor(Integer idProductColor);

}
