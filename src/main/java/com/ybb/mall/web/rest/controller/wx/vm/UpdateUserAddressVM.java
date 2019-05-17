package com.ybb.mall.web.rest.controller.wx.vm;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : 修改用户收货地址
 * @Author 黄志成
 * @Date 2019-05-13
 * @Version
 */

@ApiModel(description = "修改用户收货地址")
public class UpdateUserAddressVM {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "UpdateUserAddressVM{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", area='" + area + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
