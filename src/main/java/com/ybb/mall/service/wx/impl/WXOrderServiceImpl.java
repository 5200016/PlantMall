package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.*;
import com.ybb.mall.repository.*;
import com.ybb.mall.service.mapper.SysOrderMapper;
import com.ybb.mall.service.wx.WXOrderService;
import com.ybb.mall.web.rest.controller.wx.vm.order.LeaseProductVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SellVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitAppointmentOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitOrderVM;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.util.WxUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 微信小程序-订单管理
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

@Service
@Transactional
public class WXOrderServiceImpl implements WXOrderService {

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final AppointmentRepository appointmentRepository;

    private final ShoppingProductRepository shoppingProductRepository;

    private final SysOrderMapper orderMapper;

    private final ReceiverAddressRepository receiverAddressRepository;

    private final SUserRepository userRepository;

    public WXOrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, AppointmentRepository appointmentRepository, ShoppingProductRepository shoppingProductRepository, SysOrderMapper orderMapper, ReceiverAddressRepository receiverAddressRepository, SUserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.appointmentRepository = appointmentRepository;
        this.shoppingProductRepository = shoppingProductRepository;
        this.orderMapper = orderMapper;
        this.receiverAddressRepository = receiverAddressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResultObj insertOrder(SubmitOrderVM submitOrder) {
        return ResultObj.backCRUDSuccess("下单成功");
    }

    @Override
    public ResultObj insertAppointmentOrder(SubmitAppointmentOrderVM submitAppointmentOrder) {
        SysAppointment appointment = new SysAppointment();
        appointment.setTime(DateUtil.changeShanghaiToUTC(submitAppointmentOrder.getTime()));
        appointment.setRemark(submitAppointmentOrder.getRemark());
        appointment.setStatus(0);
        appointment.setCreateTime(DateUtil.getZoneDateTime());
        appointment.setUpdateTime(DateUtil.getZoneDateTime());

        SysUser user = new SysUser();
        user.setId(submitAppointmentOrder.getUserId());
        appointment.setUser(user);

        SysReceiverAddress receiverAddress = new SysReceiverAddress();
        receiverAddress.setId(submitAppointmentOrder.getReceiverAddressId());
        appointment.setReceiverAddress(receiverAddress);

        appointmentRepository.save(appointment);
        return ResultObj.backCRUDSuccess("预约成功");
    }

    @Override
    public ResultObj findOrderListByUserId(Long userId, Integer status, Integer pageNum, Integer pageSize) {
        Integer statusFlag = 0;
        if (!TypeUtils.isEmpty(status) && !status.equals(-1)) {
            statusFlag = 1;
        }
        return ResultObj.back(200, orderRepository.findOrderListByUserId(userId, status, statusFlag, PageRequest.of(pageNum, pageSize)).map(orderMapper::toDto));
    }

    @Override
    public ResultObj updateOrderStatusById(Long id) {
        SysOrder order = orderRepository.findSysOrderById(id);
        order.setStatus(3);
        orderRepository.save(order);
        return ResultObj.backCRUDSuccess("操作成功");
    }

    @Override
    public ResultObj findOrderServiceListByUserId(Long userId, Integer pageNum, Integer pageSize) {
        return ResultObj.back(200, orderRepository.findOrderServiceListByUserId(userId, PageRequest.of(pageNum, pageSize)).map(orderMapper::toDto));
    }

    @Override
    public ResultObj findMaintenanceByOrderId(Long id) {
        return ResultObj.back(200, orderRepository.findSysOrderById(id));
    }

    @Override
    public ResultObj findOrderListByMaintenance(Long userId, Integer pageNum, Integer pageSize) {
        return ResultObj.back(200, orderRepository.findOrderListByMaintenance(userId, PageRequest.of(pageNum, pageSize)));
    }

    @Override
    public ResultObj deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("取消成功");
    }
}
