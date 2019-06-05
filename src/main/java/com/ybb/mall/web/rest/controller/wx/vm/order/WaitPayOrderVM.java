package com.ybb.mall.web.rest.controller.wx.vm.order;

/**
 * @Description : 支付待付款订单
 * @Author 黄志成
 * @Date 2019-06-04
 * @Version
 */

public class WaitPayOrderVM {
    private String openid;
    private String totalPrice;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "WaitPayOrderVM{" +
            "openid='" + openid + '\'' +
            ", totalPrice='" + totalPrice + '\'' +
            '}';
    }
}
