package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.MaintenancePersonnelService;
import com.ybb.mall.service.wx.WXOrderService;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 绿植养护管理
 * @Author 黄志成
 * @Date 2019-05-05
 * @Version
 */

@Api(description="绿植养护管理")
@RestController
@RequestMapping("/mall")
public class MaintenanceController {
    private final MaintenancePersonnelService maintenancePersonnelService;

    private final WXOrderService wxOrderService;

    public MaintenanceController(MaintenancePersonnelService maintenancePersonnelService, WXOrderService wxOrderService) {
        this.maintenancePersonnelService = maintenancePersonnelService;
        this.wxOrderService = wxOrderService;
    }

    /**
     * 查询养护人员列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询养护人员列表")
    @GetMapping("/maintenance")
    @Timed
    public ResultObj selectMaintenanceList() throws URISyntaxException {
        return ResultObj.back(200, maintenancePersonnelService.findMaintenancePersonnelList());
    }

    /**
     * 根据订单id查询养护记录
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据订单id查询养护记录")
    @GetMapping("/maintenance/order_id")
    @Timed
    public ResultObj selectMaintenanceByOrderId(@ApiParam(name = "orderId", value = "订单id", required = true) @RequestParam Long orderId) throws URISyntaxException {
        return wxOrderService.findMaintenanceFinishByOrderId(orderId);
    }
}
