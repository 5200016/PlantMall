package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description : 修改地址状态
 * @Author 黄志成
 * @Date 2019-05-13
 * @Version
 */

public class UpdateAddressStatusVM {
    /**
     * 用户openid
     */
    private String openid;

    /**
     * 地址id
     */
    private Long id;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
