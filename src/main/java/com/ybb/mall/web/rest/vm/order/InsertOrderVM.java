package com.ybb.mall.web.rest.vm.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : 新增订单
 * @Author 黄志成
 * @Date 2019-07-12
 * @Version
 */

@ApiModel(description = "新增订单")
public class InsertOrderVM {

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
     * 收货人联系方式
     */
    @ApiModelProperty(value = "收货人联系方式")
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

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    private BigDecimal price;

    /**
     * 订单类型（0：商品订单，1：租赁订单）
     */
    @ApiModelProperty(value = "订单类型（0：商品订单，1：租赁订单）")
    private Integer type;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    private Integer number;

    /**
     * 订单描述
     */
    @ApiModelProperty(value = "订单描述")
    private String description;

    /**
     * 订单商品
     */
    @ApiModelProperty(value = "订单商品")
    private List<OrderProductVM> products;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderProductVM> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductVM> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "InsertOrderVM{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", area='" + area + '\'' +
            ", address='" + address + '\'' +
            ", price=" + price +
            ", type=" + type +
            ", number=" + number +
            ", description='" + description + '\'' +
            ", products=" + products +
            '}';
    }
}
