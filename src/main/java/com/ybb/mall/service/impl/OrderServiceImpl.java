package com.ybb.mall.service.impl;
import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.domain.SysUser;

import com.ybb.mall.domain.*;
import com.ybb.mall.repository.*;
import com.ybb.mall.service.OrderService;
import com.ybb.mall.service.dto.order.OrderDTO;
import com.ybb.mall.service.mapper.SysOrderMapper;
import com.ybb.mall.web.rest.util.*;
import com.ybb.mall.web.rest.vm.order.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 订单
 * @Author 黄志成
 * @Date 2019-04-26
 * @Version
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final MaintenancePersonnelRepository maintenancePersonnelRepository;

    private final ProductRepository productRepository;

    private final SysOrderMapper orderMapper;

    private final FormRepository formRepository;

    private final ReceiverAddressRepository receiverAddressRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderProductRepository orderProductRepository, MaintenancePersonnelRepository maintenancePersonnelRepository, ProductRepository productRepository, SysOrderMapper orderMapper, FormRepository formRepository, ReceiverAddressRepository receiverAddressRepository) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.maintenancePersonnelRepository = maintenancePersonnelRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.formRepository = formRepository;
        this.receiverAddressRepository = receiverAddressRepository;
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
    public ResultObj insertOrder(InsertOrderVM orderVM) {

        // 商品总数
        Integer productNumber = 0;
        for(OrderProductVM product : orderVM.getProducts()){
            productNumber += product.getProductNumber();
        }

        SysUser user = new SysUser();
        user.setId(orderVM.getUserId());

        // 用户收货地址信息入库
        SysReceiverAddress receiverAddress = new SysReceiverAddress();
        receiverAddress.setName(orderVM.getName());
        receiverAddress.setPhone(orderVM.getPhone());
        receiverAddress.setArea(orderVM.getArea());
        receiverAddress.setAddress(orderVM.getAddress());
        receiverAddress.setStatus(0);
        receiverAddress.setActive(true);
        receiverAddress.setCreateTime(DateUtil.getZoneDateTime());
        receiverAddress.setUpdateTime(DateUtil.getZoneDateTime());
        receiverAddress.setUser(user);
        receiverAddress = receiverAddressRepository.save(receiverAddress);

        // 订单信息入库
        SysOrder order = new SysOrder();
        order.setTradeNo(WxUtil.getTradeNoMethod());
        order.setPayNo("");
        order.setPrice(orderVM.getPrice());
        order.setType(1);
        order.setPayType(0);
        order.setStatus(0);
        order.setNumber(productNumber);
        order.setDescription(orderVM.getDescription());
        order.setMaintenancePlanStatus(0);
        order.setCreateTime(DateUtil.getZoneDateTime());
        order.setUpdateTime(DateUtil.getZoneDateTime());

        order.setUser(user);
        order.setReceiverAddress(receiverAddress);

        order = orderRepository.save(order);

        // 订单商品入库并更新商品库存
        List<SysOrderProduct> sysOrderProducts = new ArrayList<>();
        List<SysProduct> sysProducts = new ArrayList<>();
        for(OrderProductVM product : orderVM.getProducts()){
            SysProduct sysProduct = productRepository.findSysProductById(product.getProductId());
            Integer inventory = sysProduct.getInventory();
            if(inventory <= product.getProductNumber()){
                sysProduct.setInventory(0);
            }else {
                sysProduct.setInventory(inventory - product.getProductNumber());
            }

            SysOrderProduct orderProduct = new SysOrderProduct();
            orderProduct.setProductStatus(0);
            orderProduct.setProductNumber(product.getProductNumber());

            orderProduct.setOrder(order);

            SysProduct newProduct = new SysProduct();
            newProduct.setId(product.getProductId());
            orderProduct.setProduct(newProduct);

            orderProduct.setCreateTime(DateUtil.getZoneDateTime());
            orderProduct.setUpdateTime(DateUtil.getZoneDateTime());

            sysOrderProducts.add(orderProduct);
            sysProducts.add(sysProduct);
        }
        productRepository.saveAll(sysProducts);
        orderProductRepository.saveAll(sysOrderProducts);

        return ResultObj.backCRUDSuccess("新增成功");
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
        if(inventory <= reissueProductVM.getProductNumber()){
            return ResultObj.backCRUDError("补发失败，该商品库存不足");
        }else {
            sysProduct.setInventory(inventory - reissueProductVM.getProductNumber());
            productRepository.save(sysProduct);
        }

        SysOrderProduct orderProduct = new SysOrderProduct();
        orderProduct.setProductStatus(2);
        orderProduct.setProductNumber(reissueProductVM.getProductNumber());

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

    @Override
    public ResultObj setMaintenancePlan(SetMaintenanceVM setMaintenanceVM) {
        SysOrder order = orderRepository.findSysOrderById(setMaintenanceVM.getOrderId());
        order.setMaintenancePlanStatus(1);
        order.setMaintenanceDescription(setMaintenanceVM.getMaintenanceDescription());
        if(!TypeUtils.isEmpty(setMaintenanceVM.getMaintenanceTime())){
            List<String> timeList = new ArrayList<>();
            for(ZonedDateTime time : setMaintenanceVM.getMaintenanceTime()){
                timeList.add(DateUtil.zonedDateTimeFormat(time.plusHours(8), "yyyy-MM-dd"));
            }
            order.setMaintenanceTime(StringUtils.join(timeList.toArray(), ","));
        }
        SysMaintenancePersonnel sysMaintenancePersonnel = maintenancePersonnelRepository.findPersonnelById(setMaintenanceVM.getMaintenancePersonnelId());
        if(!TypeUtils.isEmpty(sysMaintenancePersonnel)){
            sysMaintenancePersonnel.setStatus(1);
            maintenancePersonnelRepository.save(sysMaintenancePersonnel);
        }
        order.setMaintenancePersonnel(sysMaintenancePersonnel);

        orderRepository.save(order);

        sendMessage(order.getUser(), "管理员已为您的绿植设置养护计划，请前往小程序核对信息");
        sendMessage(sysMaintenancePersonnel.getUser(), "管理员已为您设置养护任务，请前往小程序核对客户信息");
        return ResultObj.backCRUDSuccess("设置成功");
    }

    public void sendMessage(SysUser user, String content){
        if(!TypeUtils.isEmpty(user)){
            Long id = user.getId();
            String openid = user.getOpenid();

            List<SysForm> formList = formRepository.findFormIdByUserId(id);
            if(!TypeUtils.isEmpty(formList)){
                WXInterfaceUtil.sendMessageToUser(openid, formList.get(0).getFormId(), content);
                formRepository.delete(formList.get(0));
            }
        }
    }
}
