package com.ybb.mall.service.wx.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.domain.*;
import com.ybb.mall.repository.*;
import com.ybb.mall.service.dto.wx.MaintenanceInfoDTO;
import com.ybb.mall.service.mapper.SysOrderMapper;
import com.ybb.mall.service.wx.WXOrderService;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitAppointmentOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.review.DataVM;
import com.ybb.mall.web.rest.controller.wx.vm.review.ReviewBriefVM;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.maintenance.FinishMaintenanceVM;
import org.springframework.data.domain.Page;
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

    private final ReviewRepository reviewRepository;

    private final ReceiverAddressRepository receiverAddressRepository;

    private final SUserRepository userRepository;

    private final MaintenanceFinishRepository maintenanceFinishRepository;

    public WXOrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, AppointmentRepository appointmentRepository, ShoppingProductRepository shoppingProductRepository, SysOrderMapper orderMapper, ReviewRepository reviewRepository, ReceiverAddressRepository receiverAddressRepository, SUserRepository userRepository, MaintenanceFinishRepository maintenanceFinishRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.appointmentRepository = appointmentRepository;
        this.shoppingProductRepository = shoppingProductRepository;
        this.orderMapper = orderMapper;
        this.reviewRepository = reviewRepository;
        this.receiverAddressRepository = receiverAddressRepository;
        this.userRepository = userRepository;
        this.maintenanceFinishRepository = maintenanceFinishRepository;
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
    public ResultObj insertOrderReview(ReviewBriefVM reviewBrief) {
        SysOrder order = orderRepository.findSysOrderById(reviewBrief.getOrderId());
        order.setStatus(5);
        orderRepository.save(order);

        SysUser user = new SysUser();
        user.setId(reviewBrief.getUserId());

        List<DataVM> dataList = reviewBrief.getData();
        List<SysReview> reviewList = new ArrayList<>();
        if(!TypeUtils.isEmpty(dataList)){
            for(DataVM data : dataList){
                SysReview review = new SysReview();
                review.setContent(data.getContent());
                review.setUser(user);
                review.setCreateTime(DateUtil.getZoneDateTime());
                review.setUpdateTime(DateUtil.getZoneDateTime());

                SysProduct product = new SysProduct();
                product.setId(data.getProductId());
                review.setProduct(product);
                reviewList.add(review);
            }
            reviewRepository.saveAll(reviewList);
        }
        return ResultObj.backCRUDSuccess("发布成功");
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
    public ResultObj findOrderByOrderId(Long orderId) {
        return ResultObj.back(200, orderMapper.toDto(orderRepository.findInfoByOrderId(orderId)));
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
        Page<MaintenanceInfoDTO> maintenanceInfo = orderRepository.findOrderListByMaintenance(userId, PageRequest.of(pageNum, pageSize));
        for(MaintenanceInfoDTO data : maintenanceInfo.getContent()){
            List<SysMaintenanceFinish> maintenanceFinishes = maintenanceFinishRepository.findMaintenanceFinishByOrderId(data.getOrder().getId());
            JSONArray array = new JSONArray();
            for(SysMaintenanceFinish res : maintenanceFinishes){
                JSONObject object = new JSONObject();
                object.put("time", res.getTime());
                object.put("url", res.getUrl());
                array.add(object);
            }
            data.setFinish(array);
        }
        return ResultObj.back(200, maintenanceInfo);
    }

    @Override
    public ResultObj deleteOrderById(Long id) {
        orderRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("取消成功");
    }

    @Override
    public ResultObj insertMaintenanceTime(FinishMaintenanceVM finishMaintenance) {

        List<SysMaintenanceFinish> data = maintenanceFinishRepository.findMaintenanceFinishByOrderIdAndTime(finishMaintenance.getOrderId(), finishMaintenance.getTime());
        if(!TypeUtils.isEmpty(data)){
            return ResultObj.backCRUDError("您已提交");
        }
        SysMaintenanceFinish maintenanceFinish = new SysMaintenanceFinish();
        maintenanceFinish.setTime(finishMaintenance.getTime());
        maintenanceFinish.setUrl(finishMaintenance.getUrl());
        maintenanceFinish.setFinishTime(DateUtil.getCurrentDate("yyyy-MM-dd"));
        maintenanceFinish.setCreateTime(DateUtil.getZoneDateTime());
        maintenanceFinish.setUpdateTime(DateUtil.getZoneDateTime());

        SysOrder order = new SysOrder();
        order.setId(finishMaintenance.getOrderId());
        maintenanceFinish.setOrder(order);
        maintenanceFinishRepository.save(maintenanceFinish);
        return ResultObj.backCRUDSuccess("新增成功");
    }

    @Override
    public ResultObj findMaintenanceFinishByOrderId(Long orderId) {
        List<SysMaintenanceFinish> result = maintenanceFinishRepository.findMaintenanceFinishByOrderId(orderId);
        JSONArray array = new JSONArray();
        for(SysMaintenanceFinish data : result){
            JSONObject object = new JSONObject();
            object.put("time", data.getTime());
            object.put("url", data.getUrl());
            object.put("finishTime", data.getFinishTime());
            array.add(object);
        }
        return ResultObj.back(200, array);
    }
}
