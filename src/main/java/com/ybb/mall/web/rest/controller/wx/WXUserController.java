package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.AppointmentService;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.service.wx.WXCustomerService;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAppointmentStatusVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序-用户管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

@Api(description = "微信小程序-用户管理")
@RestController
@RequestMapping("/mall/wx")
public class WXUserController {
    private final SUserService userService;

    private final AppointmentService appointmentService;

    private final WXCustomerService wxCustomerService;

    public WXUserController(SUserService userService, AppointmentService appointmentService, WXCustomerService wxCustomerService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.wxCustomerService = wxCustomerService;
    }


    /**
     * 根据openid查询用户信息
     */
    @ApiOperation("根据openid查询用户信息")
    @GetMapping("/user")
    @Timed
    public ResultObj selectUserByOpenid(@ApiParam(name = "openid", value = "用户openid", required = true) @RequestParam String openid) throws URISyntaxException {
        return userService.findUserByOpenid(openid);
    }

    /****************************  我的预约  ****************************/

    /**
     * 根据状态分页查询预约列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询预约列表")
    @GetMapping("/user/appointment")
    @Timed
    public ResultObj selectAppointmentListByStatus(@ApiParam(name = "openid", value = "用户openid", required = true) @RequestParam String openid,
                                                   @ApiParam(name = "status", value = "预约状态（-1：全部 0：未处理，1：已处理）", required = true) @RequestParam Integer status,
                                                   @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                                   @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, appointmentService.findAppointmentListByStatus(status, openid, pageNum, pageSize));
    }

    /**
     * 修改预约订单状态
     * @param updateAppointmentStatusVM
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改预约订单状态")
    @PutMapping("/appointment/status")
    @Timed
    public ResultObj updateAppointmentStatusById(@RequestBody UpdateAppointmentStatusVM updateAppointmentStatusVM) throws URISyntaxException {
        return appointmentService.updateAppointmentById(updateAppointmentStatusVM.getId(), updateAppointmentStatusVM.getStatus());
    }

    /**
     * 客服信息查询
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("客服信息查询")
    @GetMapping("/customer_service")
    @Timed
    public ResultObj selectCustomerService() throws URISyntaxException {
        return ResultObj.back(200, wxCustomerService.findAll().get(0));
    }
}
