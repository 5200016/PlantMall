package com.ybb.mall.repository;

import com.ybb.mall.service.dto.order.OrderDTO;
import org.springframework.stereotype.Repository;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.dto.order.OrderListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

/**
 * @Description : 订单
 * @Author 黄志成
 * @Date 2019-04-26
 * @Version
 */

@Repository
public interface OrderRepository extends JpaRepository<SysOrder, Long> {

    /**
     * 分页查询订单列表
     * 条件：订单号（模糊查询），订单类型，订单状态，收货人、手机号
     */
    @Query(
        value = "select so" +
            " from SysOrder so" +
            " left join fetch so.user" +
            " left join fetch so.orderProducts" +
            " left join fetch so.receiverAddress" +
            " where so.tradeNo like concat('%', ?1, '%')" +
            " and so.type = ?2" +
            " and (0 = ?4 or so.status = ?3)" +
            " and (so.receiverAddress.name like concat('%', ?5, '%') or so.receiverAddress.phone like concat('%', ?5, '%'))" +
            " order by so.createTime desc",
        countQuery = "select count(so) from SysOrder so" +
            " order by so.createTime desc"
    )
    Page<SysOrder> findOrderList(String tradeNo, Integer type, Integer status, Integer statusFlag, String value, Pageable pageable);
}
