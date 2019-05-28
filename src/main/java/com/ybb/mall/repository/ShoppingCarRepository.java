package com.ybb.mall.repository;

import com.ybb.mall.domain.SysShoppingCar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * 购物车
 */
@Repository
public interface ShoppingCarRepository extends JpaRepository<SysShoppingCar, Long> {
    /**
     * 分页查询订单列表
     * 条件：订单号（模糊查询），订单类型，订单状态，收货人、手机号
     */
    @Query(
        value = "select ssc" +
            " from SysShoppingCar ssc" +
            " left join fetch ssc.shoppingProducts" +
            " where ssc.user.id = ?1" +
            " order by ssc.type desc, ssc.createTime desc",
        countQuery = "select count(ssc) from SysShoppingCar ssc" +
            " order by ssc.createTime desc, ssc.type desc"
    )
    Page<SysShoppingCar> findShoppingCarList(Long userId, Pageable pageable);
}
