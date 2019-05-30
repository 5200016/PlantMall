package com.ybb.mall.repository;

import com.ybb.mall.service.dto.order.OrderDTO;
import org.springframework.stereotype.Repository;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.dto.order.OrderListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

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
            " left join fetch so.receiverAddress" +
            " left join fetch so.maintenancePersonnel" +
            " where so.tradeNo like concat('%', ?1, '%')" +
            " and so.type = ?2" +
            " and (0 = ?4 or so.status = ?3)" +
            " and (so.receiverAddress.name like concat('%', ?5, '%') or so.receiverAddress.phone like concat('%', ?5, '%'))" +
            " order by so.createTime desc",
        countQuery = "select count(so) from SysOrder so" +
            " order by so.createTime desc"
    )
    Page<SysOrder> findOrderList(String tradeNo, Integer type, Integer status, Integer statusFlag, String value, Pageable pageable);

    /**
     * 根据订单id查询
     */
    SysOrder findSysOrderById(Long id);

    /**
     * 分页查询订单列表（微信小程序）
     * 条件：用户id订单状态
     */
    @Query(
        value = "select so" +
            " from SysOrder so" +
            " left join fetch so.user" +
            " left join fetch so.receiverAddress" +
            " left join fetch so.orderProducts" +
            " where so.user.id = ?1" +
            " and (0 = ?3 or so.status = ?2)" +
            " order by so.createTime desc",
        countQuery = "select count(so) from SysOrder so" +
            " order by so.createTime desc"
    )
    Page<SysOrder> findOrderListByUserId(Long userId, Integer status, Integer statusFlag, Pageable pageable);

    /**
     * 分页查询用户养护计划列表（微信小程序）
     * 条件：用户id
     */
    @Query(
        value = "select so" +
            " from SysOrder so" +
            " left join fetch so.user" +
            " left join fetch so.receiverAddress" +
            " left join fetch so.orderProducts" +
            " where so.user.id = ?1" +
            " and so.type = 1" +
            " and so.status = 3" +
            " order by so.createTime desc",
        countQuery = "select count(so) from SysOrder so" +
            " order by so.createTime desc"
    )
    Page<SysOrder> findOrderServiceListByUserId(Long userId, Pageable pageable);

    /**
     * 养护人员分页查询订单列表
     */
    @Query("select so from" +
        " SysMaintenancePersonnel smp left join SysOrder so on smp.id = so.maintenancePersonnel.id" +
        " where smp.user.id = ?1" +
        " and so.id is not null" +
        " order by so.createTime desc")
    Page<SysOrder> findOrderListByMaintenance(Long userId, Pageable pageable);
}
