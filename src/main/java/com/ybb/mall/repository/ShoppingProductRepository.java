package com.ybb.mall.repository;

import com.ybb.mall.domain.SysShoppingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 购物车商品
 */
@Repository
public interface ShoppingProductRepository extends JpaRepository<SysShoppingProduct, Long> {
    @Query("select ssp from SysShoppingProduct ssp where ssp.id = ?1")
    SysShoppingProduct findShoppingProductById(Long id);
}
