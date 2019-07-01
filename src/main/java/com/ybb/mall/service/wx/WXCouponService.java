package com.ybb.mall.service.wx;

import com.ybb.mall.web.rest.controller.wx.vm.coupon.GetCouponVM;
import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description : 微信小程序-优惠券管理
 * @Author 黄志成
 * @Date 2019-06-11
 * @Version
 */

public interface WXCouponService {

    /**
     * 分页查询优惠券列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultObj findCouponList(Integer pageNum, Integer pageSize);

    /**
     * 添加用户领取优惠券记录
     * @param couponVM
     * @return
     */
    ResultObj insertUserOfCoupon(GetCouponVM couponVM);

    /**
     * 我的优惠券功能查询
     */
    ResultObj findMyCouponList(Long userId, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 订单优惠券列表查询
     */
    ResultObj findOrderCouponList(Long userId);
}
