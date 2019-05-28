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
import org.springframework.data.domain.PageRequest;
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

        // 处理出售商品订单
        List<SysOrderProduct> orderProductList = new ArrayList<>();
        List<SellVM> sellVMList = submitOrder.getSell();
        for(SellVM sellVM : sellVMList){
            // 入库订单表
            SysOrder order = new SysOrder();
            order.setTradeNo(WxUtil.getTradeNoMethod());
            order.setPayNo(submitOrder.getPayNo());
            order.setPrice(sellVM.getTotalPrice());
            order.setType(0);
            order.setPayType(0);
            order.setStatus(1);
            order.setNumber(sellVM.getNumber());
            order.setDescription(sellVM.getDescription());
            order.setMaintenancePlanStatus(0);

            SysUser user = new SysUser();
            user.setId(submitOrder.getUserId());
            order.setUser(user);

            SysReceiverAddress receiverAddress = new SysReceiverAddress();
            receiverAddress.setId(submitOrder.getReceiveAddressId());
            order.setReceiverAddress(receiverAddress);

            order.setCreateTime(DateUtil.getZoneDateTime());
            order.setUpdateTime(DateUtil.getZoneDateTime());

            SysOrder sysOrder = orderRepository.save(order);

            SysOrderProduct orderProduct = new SysOrderProduct();
            orderProduct.setProductStatus(0);
            orderProduct.setOrder(sysOrder);
            orderProduct.setProductNumber(sellVM.getNumber());

            SysProduct product = new SysProduct();
            product.setId(sellVM.getProductId());
            orderProduct.setProduct(product);

            orderProduct.setUpdateTime(DateUtil.getZoneDateTime());
            orderProduct.setCreateTime(DateUtil.getZoneDateTime());
            orderProductList.add(orderProduct);
        }


        // 处理租赁商品
        if(!TypeUtils.isEmpty(submitOrder.getLease().getProductList())){
            SysOrder order = new SysOrder();
            order.setTradeNo(WxUtil.getTradeNoMethod());
            order.setPrice(submitOrder.getLease().getTotalPrice());
            order.setPayNo(submitOrder.getPayNo());
            order.setStatus(1);
            order.setType(1);
            order.setPayType(0);
            order.setNumber(submitOrder.getLease().getNumber());
            order.setMaintenancePlanStatus(0);
            order.setDescription(submitOrder.getLease().getDescription());
            order.setUpdateTime(DateUtil.getZoneDateTime());
            order.setCreateTime(DateUtil.getZoneDateTime());

            SysReceiverAddress receiverAddress = new SysReceiverAddress();
            receiverAddress.setId(submitOrder.getReceiveAddressId());
            order.setReceiverAddress(receiverAddress);

            SysUser user = new SysUser();
            user.setId(submitOrder.getUserId());
            order.setUser(user);

            SysOrder sysOrder = orderRepository.save(order);
            for(LeaseProductVM data : submitOrder.getLease().getProductList()){
                SysOrderProduct orderProduct = new SysOrderProduct();
                orderProduct.setProductStatus(0);
                orderProduct.setOrder(sysOrder);
                orderProduct.setProductNumber(data.getProductNumber());
                orderProduct.setUpdateTime(DateUtil.getZoneDateTime());
                orderProduct.setCreateTime(DateUtil.getZoneDateTime());

                SysProduct product = new SysProduct();
                product.setId(data.getProductId());
                orderProduct.setProduct(product);

                orderProductList.add(orderProduct);
            }
        }
        orderProductRepository.saveAll(orderProductList);

        if(!TypeUtils.isEmpty(submitOrder.getShoppingProductIdList())){
            List<SysShoppingProduct> list = new ArrayList<>();
            for(Long id : submitOrder.getShoppingProductIdList()){
                SysShoppingProduct object = new SysShoppingProduct();
                object.setId(id);
                list.add(object);
            }
            shoppingProductRepository.deleteInBatch(list);
        }

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
}
