package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description : 微信用户信息
 * @Author 黄志成
 * @Date 2019-05-31
 * @Version
 */

public class WXUserVM {
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WXUserVM{" +
            "openid='" + openid + '\'' +
            '}';
    }
}
