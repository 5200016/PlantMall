package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.WXService;
import com.ybb.mall.service.wx.WXFormService;
import com.ybb.mall.web.rest.controller.wx.vm.PayFinishVM;
import com.ybb.mall.web.rest.controller.wx.vm.WXBindVM;
import com.ybb.mall.web.rest.controller.wx.vm.WXFormVM;
import com.ybb.mall.web.rest.controller.wx.vm.WXUserVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.SubmitOrderVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.UpdateOrderStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.order.WaitPayOrderVM;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.WxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @Description : 微信小程序管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

@Api(description="微信小程序管理")
@RestController
@RequestMapping("/mall/wx")
public class WXController {

    private final WXService wxService;

    private final WXFormService wxFormService;

    public WXController(WXService wxService, WXFormService wxFormService) {
        this.wxService = wxService;
        this.wxFormService = wxFormService;
    }

    /**
     * 绑定用户信息
     */
    @ApiOperation("绑定用户信息")
    @PostMapping("/bind_user")
    @Timed
    public ResultObj bindUser(@RequestBody WXBindVM wxBindVM) throws URISyntaxException {
        return wxService.decodeUserInfo(wxBindVM.getEncryptedData(), wxBindVM.getIv(), wxBindVM.getCode());
    }

    /**
     * 绑定用户手机号
     */
    @ApiOperation("绑定用户手机号")
    @PostMapping("/bind_phone")
    @Timed
    public ResultObj bindPhone(@RequestBody WXBindVM wxBindVM) throws URISyntaxException {
        return wxService.decodeUserPhone(wxBindVM.getOpenid(), wxBindVM.getEncryptedData(), wxBindVM.getIv(), wxBindVM.getCode());
    }

    /**
     * 微信formId存储
     */
    @ApiOperation("微信formId存储")
    @PostMapping("/form")
    @Timed
    public ResultObj saveFormId(@RequestBody WXFormVM wxFormVM) throws URISyntaxException {
        return wxFormService.insertForm(wxFormVM);
    }

    /**
     * 微信支付
     */
    @ApiOperation("微信支付")
    @PostMapping("/pay")
    @Timed
    public ResultObj wxPay(@RequestBody SubmitOrderVM submitOrder) throws Exception {
        return wxService.wxPayMethod(submitOrder);
    }

    /**
     * 微信支付回调
     */
    @ApiOperation("微信支付回调")
    @RequestMapping(value = "/notifyCallback")
    public void notifyCallback(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("微信支付回调");
        try {
            synchronized (this) {
                Map<String, String> kvm = WxUtil.parseRequestXmlToMap(request);

                //回调支付订单号
                String orderCode = kvm.get("transaction_id");

                //回调支付是否成功状态码
                String resultCode = kvm.get("result_code");

                //回调用户openid
                String openid = kvm.get("openid");

                if("SUCCESS".equals(resultCode)){
                    // userService.updateStudentPayStatus(openid, orderCode);
                    response.getWriter().write(WxUtil.WX_PAY_SUCCESS);
                }else {
                    response.getWriter().write(WxUtil.WX_PAY_FAIL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付完成修改订状态
     */
    @ApiOperation("支付完成修改订状态")
    @PostMapping("/pay/finish")
    @Timed
    public ResultObj payFinish(@RequestBody SubmitOrderVM submitOrder) throws URISyntaxException {
        return wxService.payFinishMethod(submitOrder);
    }

    /**
     * 支付待付款订单
     */
    @ApiOperation("支付待付款订单")
    @PostMapping("/pay/wait_pay")
    @Timed
    public ResultObj payWaitPayOrder(@RequestBody WaitPayOrderVM payOrder) throws Exception {
        return wxService.payWaitPayOrder(payOrder);
    }

    /**
     * 改变待付款订单状态为待发货
     */
    @ApiOperation("改变待付款订单状态为待发货")
    @PutMapping("/pay/wait_pay")
    @Timed
    public ResultObj updateWaitPayOrder(@RequestBody UpdateOrderStatusVM updateOrderStatusVM) throws Exception {
        return wxService.updateWaitPayOrder(updateOrderStatusVM);
    }
}
