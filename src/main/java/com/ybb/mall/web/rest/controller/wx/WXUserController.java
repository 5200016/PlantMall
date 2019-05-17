package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.AppointmentService;
import com.ybb.mall.service.ReceiverAddressService;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAppointmentStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
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

    private final ReceiverAddressService receiverAddressService;

    public WXUserController(SUserService userService, AppointmentService appointmentService, ReceiverAddressService receiverAddressService) {
        this.userService = userService;
        this.appointmentService = appointmentService;
        this.receiverAddressService = receiverAddressService;
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

    /****************************  收货地址  ****************************/

    /**
     * 根据openid查询用户收货地址列表
     */
    @ApiOperation("根据openid查询用户收货地址列表")
    @GetMapping("/user/address")
    @Timed
    public ResultObj selectUserAddressByOpenid(@ApiParam(name = "openid", value = "用户openid", required = true) @RequestParam String openid,
                                               @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                               @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return receiverAddressService.findUserAddressByOpenid(openid, pageNum, pageSize);
    }

    /**
     * 新增用户收货地址信息
     */
    @ApiOperation("新增用户收货地址信息")
    @PostMapping("/user/address")
    @Timed
    public ResultObj createUserAddress(@RequestBody InsertUserAddressVM insertUserAddressVM) throws URISyntaxException {
        return receiverAddressService.insertUserAddress(insertUserAddressVM);
    }

    /**
     * 编辑用户收货地址信息
     */
    @PutMapping("/user/address")
    @Timed
    public ResultObj updateUserAddress(@RequestBody UpdateUserAddressVM updateUserAddressVM) throws URISyntaxException {
        return receiverAddressService.updateUserAddress(updateUserAddressVM);
    }

    /**
     * 根据id查询用户收货地址
     */
    @ApiOperation("根据id查询用户收货地址")
    @GetMapping("/user/address/{id}")
    @Timed
    public ResultObj selectUserAddressById(@ApiParam(name = "id", value = "主键", required = true) @PathVariable Long id) throws URISyntaxException {
        return receiverAddressService.findAddressById(id);
    }

    /**
     * 根据id删除用户收货地址
     */
    @ApiOperation("根据id查询用户地址")
    @DeleteMapping("/user/address/{id}")
    @Timed
    public ResultObj deleteUserAddressById(@ApiParam(name = "id", value = "主键", required = true) @PathVariable Long id) throws URISyntaxException {
        return receiverAddressService.deleteUserAddress(id);
    }

    /**
     * 根据openid及地址id修改默认收货地址
     */
    @ApiOperation("根据openid及地址id修改默认收货地址")
    @PutMapping("/user/address/status")
    @Timed
    public ResultObj updateAddressByOpenid(@RequestBody UpdateAddressStatusVM updateAddressStatusVM) throws URISyntaxException {
        return receiverAddressService.updateAddressByOpenid(updateAddressStatusVM);
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
}
