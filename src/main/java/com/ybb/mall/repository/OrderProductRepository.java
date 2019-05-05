package com.ybb.mall.repository;

import com.ybb.mall.domain.SysOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 订单商品
 */
@SuppressWarnings("unused")
@Repository
public interface OrderProductRepository extends JpaRepository<SysOrderProduct, Long> {

    /**
     * 根据订单id查询订单商品
     */
    List<SysOrderProduct> findSysOrderProductsByOrder_Id(Long orderId);
}
