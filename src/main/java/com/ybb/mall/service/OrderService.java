package com.ybb.mall.service;

import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.service.dto.order.OrderDTO;
import com.ybb.mall.service.dto.order.OrderListDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.order.OrderVM;
import com.ybb.mall.web.rest.vm.order.ReissueProductVM;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-26
 * @Version
 */

public interface OrderService {

    /**
     * 分页查询订单列表
     * 条件：订单号（模糊查询），订单类型，订单状态，用户昵称、姓名、手机号（模糊查询）
     */
    Page<OrderDTO> findOrderList(String tradeNo, Integer type, Integer status, String value, Integer pageNum, Integer pageSize);

    /**
     * 批量发货
     * @param orderList
     * @return
     */
    ResultObj shipmentsBatch(List<OrderVM> orderList);

    /**
     * 批量删除订单商品（逻辑删除）
     * @param orderProducts
     * @return
     */
    ResultObj deleteOrderProductBatch(List<SysOrderProduct> orderProducts);

    /**
     * 补发订单商品
     * @param reissueProductVM
     * @return
     */
    ResultObj insertOrderProduct(ReissueProductVM reissueProductVM);

    /**
     * 根据订单id查询订单商品
     * @param orderId
     * @return
     */
    List<SysOrderProduct> findOrderProductByOrderId(Long orderId);

}
