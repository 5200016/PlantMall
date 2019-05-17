package com.ybb.mall.web.rest.controller.wx.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : 新增用户收货地址
 * @Author 黄志成
 * @Date 2019-05-13
 * @Version
 */

@ApiModel(description = "新增用户收货地址")
public class InsertUserAddressVM {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 收货地区
     */
    @ApiModelProperty(value = "收货地区")
    private String area;

    /**
     * 收货详细地址
     */
    @ApiModelProperty(value = "收货详细地址")
    private String address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "InsertUserAddressVM{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", area='" + area + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
