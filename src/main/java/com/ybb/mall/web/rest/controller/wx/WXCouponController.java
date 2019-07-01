package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.wx.WXCouponService;
import com.ybb.mall.web.rest.controller.wx.vm.coupon.GetCouponVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序-优惠券管理
 * @Author 黄志成
 * @Date 2019-06-11
 * @Version
 */

@Api(description="微信小程序-优惠券管理")
@RestController
@RequestMapping("/mall/wx")
public class WXCouponController {

    private final WXCouponService wxCouponService;

    public WXCouponController(WXCouponService wxCouponService) {
        this.wxCouponService = wxCouponService;
    }

    /**
     * 查询优惠券列表
     */
    @ApiOperation("查询优惠券列表")
    @GetMapping("/coupon")
    @Timed
    public ResultObj selectCouponList(@ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                      @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return wxCouponService.findCouponList(pageNum, pageSize);
    }

    /**
     * 我的优惠券功能查询
     */
    @ApiOperation("我的优惠券功能查询")
    @GetMapping("/coupon/my")
    @Timed
    public ResultObj selectMyCouponList(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam Long userId,
                                        @ApiParam(name = "status", value = "使用状态", required = true) @RequestParam Integer status,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                        @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return wxCouponService.findMyCouponList(userId, status, pageNum, pageSize);
    }

    /**
     * 订单优惠券列表查询
     */
    @ApiOperation("订单优惠券列表查询")
    @GetMapping("/coupon/order")
    @Timed
    public ResultObj selectOrderCouponList(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam Long userId) throws URISyntaxException {
        return wxCouponService.findOrderCouponList(userId);
    }

    /**
     * 领取优惠券
     */
    @ApiOperation("领取优惠券")
    @PostMapping("/coupon")
    @Timed
    public ResultObj getCoupon(@RequestBody GetCouponVM couponVM) throws URISyntaxException {
        return wxCouponService.insertUserOfCoupon(couponVM);
    }
}
