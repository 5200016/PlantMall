package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.service.OrderService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.order.OrderVM;
import com.ybb.mall.web.rest.vm.order.ReissueProductVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @Description :订单管理
 * @Author 黄志成
 * @Date 2019-04-25
 * @Version
 */

@Api(description="订单管理")
@RestController
@RequestMapping("/mall")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 分页查询订单列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询订单列表")
    @GetMapping("/order")
    @Timed
    public ResultObj selectOrderList(@ApiParam(name = "tradeNo", value = "订单号", required = true) @RequestParam String tradeNo,
                                     @ApiParam(name = "type", value = "订单类型", required = true) @RequestParam Integer type,
                                     @ApiParam(name = "status", value = "订单状态（0：商品订单，1：租赁订单）", required = true) @RequestParam Integer status,
                                     @ApiParam(name = "value", value = "用户昵称、姓名、手机号", required = true) @RequestParam String value,
                                     @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                     @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, orderService.findOrderList(tradeNo, type, status, value, pageNum, pageSize));
    }

    /**
     * 批量发货
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("批量发货")
    @PutMapping("/order/shipments")
    @Timed
    public ResultObj shipmentsBatch(@RequestBody List<OrderVM> orderList) throws URISyntaxException {
        return orderService.shipmentsBatch(orderList);
    }

    /**
     * 根据订单id查询订单商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据订单id查询订单商品")
    @GetMapping("/order/products")
    @Timed
    public ResultObj selectOrderProductByOrderId(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam Long orderId) throws URISyntaxException {
        return ResultObj.back(200, orderService.findOrderProductByOrderId(orderId));
    }

    /**
     * 补发订单商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("补发订单商品")
    @PostMapping("/order/reissue")
    @Timed
    public ResultObj reissueProducts(@RequestBody ReissueProductVM reissueProductVM) throws URISyntaxException {
        return orderService.insertOrderProduct(reissueProductVM);
    }

    /**
     * 批量删除订单商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("批量删除订单商品")
    @PutMapping("/order/products")
    @Timed
    public ResultObj deleteOrderProductBatch(@RequestBody List<SysOrderProduct> orderProducts) throws URISyntaxException {
        return orderService.deleteOrderProductBatch(orderProducts);
    }
}
