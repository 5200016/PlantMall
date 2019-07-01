package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.wx.WXReceiverAddressService;
import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序-收货地址管理
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

@Api(description = "微信小程序-收货地址管理")
@RestController
@RequestMapping("/mall/wx")
public class WXReceiverAddressController {

    private final WXReceiverAddressService wxReceiverAddressService;

    public WXReceiverAddressController(WXReceiverAddressService wxReceiverAddressService) {
        this.wxReceiverAddressService = wxReceiverAddressService;
    }


    /**
     * 根据openid查询用户收货地址列表
     */
    @ApiOperation("根据openid查询用户收货地址列表")
    @GetMapping("/address")
    @Timed
    public ResultObj selectUserAddressByOpenid(@ApiParam(name = "openid", value = "用户openid", required = true) @RequestParam String openid,
                                               @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                               @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return wxReceiverAddressService.findUserAddressByOpenid(openid, pageNum, pageSize);
    }

    /**
     * 新增用户收货地址信息
     */
    @ApiOperation("新增用户收货地址信息")
    @PostMapping("/address")
    @Timed
    public ResultObj createUserAddress(@RequestBody InsertUserAddressVM insertUserAddressVM) throws URISyntaxException {
        return wxReceiverAddressService.insertUserAddress(insertUserAddressVM);
    }

    /**
     * 编辑用户收货地址信息
     */
    @PutMapping("/address")
    @Timed
    public ResultObj updateUserAddress(@RequestBody UpdateUserAddressVM updateUserAddressVM) throws URISyntaxException {
        return wxReceiverAddressService.updateUserAddress(updateUserAddressVM);
    }

    /**
     * 根据id查询收货地址
     */
    @ApiOperation("根据id查询收货地址")
    @GetMapping("/address/{id}")
    @Timed
    public ResultObj selectUserAddressById(@ApiParam(name = "id", value = "主键", required = true) @PathVariable Long id) throws URISyntaxException {
        return wxReceiverAddressService.findAddressById(id);
    }

    /**
     * 根据id删除用户收货地址
     */
    @ApiOperation("根据id查询用户地址")
    @DeleteMapping("/address/{id}")
    @Timed
    public ResultObj deleteUserAddressById(@ApiParam(name = "id", value = "主键", required = true) @PathVariable Long id) throws URISyntaxException {
        return wxReceiverAddressService.deleteUserAddress(id);
    }

    /**
     * 根据openid及地址id修改默认收货地址
     */
    @ApiOperation("根据openid及地址id修改默认收货地址")
    @PutMapping("/address/status")
    @Timed
    public ResultObj updateAddressByOpenid(@RequestBody UpdateAddressStatusVM updateAddressStatusVM) throws URISyntaxException {
        return wxReceiverAddressService.updateAddressByOpenid(updateAddressStatusVM);
    }

}
