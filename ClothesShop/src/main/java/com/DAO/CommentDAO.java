package com.DAO;

import com.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Integer> {
    @Query("select  o from Comment  o where o.product.idProduct=?1")
    List<Comment> findAllByIdProduct(Integer idProduct);
}
