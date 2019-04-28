package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.repository.OrderRepository;
import com.ybb.mall.service.OrderService;
import com.ybb.mall.service.dto.order.OrderListDTO;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.order.OrderVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-26
 * @Version
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<OrderListDTO> findOrderList(String tradeNo, Integer type, Integer status, String value, Integer pageNum, Integer pageSize) {
        Integer statusFlag = 0;
        if (!TypeUtils.isEmpty(status) && !status.equals(-1)) {
            statusFlag = 1;
        }
        return orderRepository.findOrderList(tradeNo, type, status, statusFlag, value, PageRequest.of(pageNum, pageSize));

    }

    @Override
    public ResultObj shipmentsBatch(List<OrderVM> orderList) {
        if(TypeUtils.isEmpty(orderList)){
            return ResultObj.backCRUDError("请选择订单");
        }
        List<SysOrder> orders = new ArrayList<>();
        for(OrderVM data : orderList){
            if(data.getStatus().equals(1)){
                SysOrder order = new SysOrder();
                order.setId(data.getId());
                order.setTradeNo(data.getTradeNo());
                order.setPayNo(data.getPayNo());
                order.setPayType(data.getPayType());
                order.setPrice(data.getPrice());
                order.setType(data.getType());
                order.setStatus(2);
                order.setNumber(data.getNumber());
                order.setDescription(data.getDescription());
                order.setMaintenancePlanStatus(data.getMaintenancePlanStatus());
                order.setCreateTime(data.getCreateTime());
                order.setUpdateTime(DateUtil.getZoneDateTime());
                order.setUser(data.getUser());
                order.setProduct(data.getProduct());
                order.setReceiverAddress(data.getReceiverAddress());
                orders.add(order);
            }
        }
        orderRepository.saveAll(orders);
        return ResultObj.backCRUDSuccess("发货成功");
    }
}
