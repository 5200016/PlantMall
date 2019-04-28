package com.ybb.mall.service;

import com.ybb.mall.service.dto.order.OrderListDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.order.OrderVM;
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
    Page<OrderListDTO> findOrderList(String tradeNo, Integer type, Integer status, String value, Integer pageNum, Integer pageSize);

    /**
     * 批量发货
     * @param orderList
     * @return
     */
    ResultObj shipmentsBatch(List<OrderVM> orderList);
}
