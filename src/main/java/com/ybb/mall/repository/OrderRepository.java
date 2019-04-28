package com.ybb.mall.repository;

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
public interface OrderRepository extends JpaRepository<SysOrder, Long>{
    /**
     * 分页查询订单列表
     * 条件：订单号（模糊查询），订单类型，订单状态，用户昵称、姓名、手机号
     */
    @Query("select new com.ybb.mall.service.dto.order.OrderListDTO(so.id, so.tradeNo, so.payNo, so.price, so.type, so.payType, so.status, so.number, so.description, so.maintenancePlanStatus, so.createTime, su, sp, sra)" +
        " from SysOrder so" +
        " left join SysUser su on so.user.id = su.id" +
        " left join SysProduct sp on so.product.id = sp.id" +
        " left join SysReceiverAddress sra on so.receiverAddress.id = sra.id" +
        " where so.tradeNo like concat('%', ?1, '%')" +
        " and so.type = ?2" +
        " and (0 = ?4 or so.status = ?3)" +
        " and (su.nickname like concat('%', ?5, '%') or su.username like concat('%', ?5, '%') or su.phone like concat('%', ?5, '%'))" +
        " order by so.createTime desc")
    Page<OrderListDTO> findOrderList(String tradeNo, Integer type, Integer status, Integer statusFlag, String value, Pageable pageable);
}
