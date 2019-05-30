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

    /**
     * 根据商品id及类型查询购物车商品
     */
    @Query("select ssp from" +
        " SysShoppingProduct ssp left join SysShoppingCar ssc on ssp.shoppingCar.id = ssc.id" +
        " where ssc.user.id = ?1" +
        " and ssp.product.id = ?2" +
        " and ssp.productType = ?3")
    SysShoppingProduct findShoppingProduct(Long userId, Long productId, Integer productType);
}
