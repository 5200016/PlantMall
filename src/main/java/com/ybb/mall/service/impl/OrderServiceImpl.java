package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.OrderProductRepository;
import com.ybb.mall.repository.OrderRepository;
import com.ybb.mall.repository.ProductRepository;
import com.ybb.mall.service.OrderService;
import com.ybb.mall.service.dto.order.OrderDTO;
import com.ybb.mall.service.mapper.SysOrderMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.order.OrderVM;
import com.ybb.mall.web.rest.vm.order.ReissueProductVM;
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

    private final OrderProductRepository orderProductRepository;

    private final ProductRepository productRepository;

    private final SysOrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, ProductRepository productRepository, SysOrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Page<OrderDTO> findOrderList(String tradeNo, Integer type, Integer status, String value, Integer pageNum, Integer pageSize) {
        Integer statusFlag = 0;
        if (!TypeUtils.isEmpty(status) && !status.equals(-1)) {
            statusFlag = 1;
        }
        return orderRepository.findOrderList(tradeNo, type, status, statusFlag, value, PageRequest.of(pageNum, pageSize)).map(orderMapper::toDto);
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
                order.setReceiverAddress(data.getReceiverAddress());
                orders.add(order);
            }
        }
        orderRepository.saveAll(orders);
        return ResultObj.backCRUDSuccess("发货成功");
    }

    @Override
    public ResultObj deleteOrderProductBatch(List<SysOrderProduct> orderProducts) {
        if(TypeUtils.isEmpty(orderProducts)){
            return ResultObj.backCRUDError("请选择商品");
        }
        for (SysOrderProduct data : orderProducts){
            data.setProductStatus(1);
        }
        orderProductRepository.saveAll(orderProducts);
        return ResultObj.backCRUDSuccess("删除成功");
    }

    @Override
    public ResultObj insertOrderProduct(ReissueProductVM reissueProductVM) {
        if (TypeUtils.isEmpty(reissueProductVM.getOrderId()) || TypeUtils.isEmpty(reissueProductVM.getProductId())) {
            return ResultObj.backCRUDError("补发失败，请核对数据");
        }

        // 更新商品库存
        SysProduct sysProduct = productRepository.findSysProductById(reissueProductVM.getProductId());
        Integer inventory = sysProduct.getInventory();
        if(inventory <= 0){
            return ResultObj.backCRUDError("补发失败，该商品库存不足");
        }else {
            sysProduct.setInventory(inventory - 1);
            productRepository.save(sysProduct);
        }

        SysOrderProduct orderProduct = new SysOrderProduct();
        orderProduct.setProductStatus(2);

        SysProduct product = new SysProduct();
        product.setId(reissueProductVM.getProductId());
        orderProduct.setProduct(product);

        SysOrder order = new SysOrder();
        order.setId(reissueProductVM.getOrderId());
        orderProduct.setOrder(order);

        orderProduct.setCreateTime(DateUtil.getZoneDateTime());
        orderProduct.setUpdateTime(DateUtil.getZoneDateTime());

        orderProductRepository.save(orderProduct);
        return ResultObj.backCRUDSuccess("补发成功");
    }

    @Override
    public List<SysOrderProduct> findOrderProductByOrderId(Long orderId) {
        return orderProductRepository.findSysOrderProductsByOrder_Id(orderId);
    }
}
