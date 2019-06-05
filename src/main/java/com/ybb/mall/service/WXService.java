package com.ybb.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.web.rest.controller.wx.vm.PayFinishVM;
import com.ybb.mall.web.rest.controller.wx.vm.WXUserVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.UpdateOrderStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.WaitPayOrderVM;
import com.ybb.mall.web.rest.util.ResultObj;

import java.io.UnsupportedEncodingException;

/**
 * @Description : 微信小程序管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

public interface WXService {

    /**
     * 解析用户信息
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    ResultObj decodeUserInfo(String encryptedData, String iv, String code);

    /**
     * 解析用户手机号
     * @param openid
     * @param encryptedData
     * @param iv
     * @return
     */
    ResultObj decodeUserPhone(String openid, String encryptedData, String iv, String code);

    /**
     * 微信支付
     * @param submitOrder
     * @return
     */
    ResultObj wxPayMethod(SubmitOrderVM submitOrder) throws Exception;

    /**
     * 支付完成修改订状态并删除购物车
     * @param submitOrder
     * @return
     */
    ResultObj payFinishMethod(SubmitOrderVM submitOrder);

    /**
     * 支付待付款订单
     * @param waitPayOrderVM
     * @return
     */
    ResultObj payWaitPayOrder(WaitPayOrderVM waitPayOrderVM) throws Exception;

    /**
     * 修改未支付订单状态
     * @param orderStatusVM
     * @return
     */
    ResultObj updateWaitPayOrder(UpdateOrderStatusVM orderStatusVM);
}
