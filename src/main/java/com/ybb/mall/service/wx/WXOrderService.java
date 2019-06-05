package com.ybb.mall.service.wx;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.dto.order.OrderDTO;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitAppointmentOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitOrderVM;
import com.ybb.mall.web.rest.util.ResultObj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Description : 微信小程序-订单管理
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

public interface WXOrderService {

    /**
     * 新增订单
     * @param submitOrder
     * @return
     */
    ResultObj insertOrder(SubmitOrderVM submitOrder);

    /**
     * 新增预约订单
     * @param submitAppointmentOrder
     * @return
     */
    ResultObj insertAppointmentOrder(SubmitAppointmentOrderVM submitAppointmentOrder);

    /**
     * 根据用户id查询订单列表
     * @param userId
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultObj findOrderListByUserId(Long userId, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 修改订单状态
     * @param id
     * @return
     */
    ResultObj updateOrderStatusById(Long id);

    /**
     * 根据用户id查询租赁订单绿植养护列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultObj findOrderServiceListByUserId(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 根据订单id查询养护计划
     * @param id
     * @return
     */
    ResultObj findMaintenanceByOrderId(Long id);

    /**
     * 养护人员分页查询订单列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultObj findOrderListByMaintenance(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 删除订单
     */
    ResultObj deleteOrderById(Long id);
}
