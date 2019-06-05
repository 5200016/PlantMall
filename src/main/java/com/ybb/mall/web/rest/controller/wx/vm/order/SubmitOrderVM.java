package com.ybb.mall.web.rest.controller.wx.vm.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : 提交订单
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

public class SubmitOrderVM {
    private List<SellVM> sell;
    private LeaseVM lease;
    private Long userId;
    private String payPrice;
    private String openid;
    private Long receiveAddressId;
    private String payNo;
    private List<Long> shoppingProductIdList;

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public List<Long> getShoppingProductIdList() {
        return shoppingProductIdList;
    }

    public void setShoppingProductIdList(List<Long> shoppingProductIdList) {
        this.shoppingProductIdList = shoppingProductIdList;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<SellVM> getSell() {
        return sell;
    }

    public void setSell(List<SellVM> sell) {
        this.sell = sell;
    }

    public LeaseVM getLease() {
        return lease;
    }

    public void setLease(LeaseVM lease) {
        this.lease = lease;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Long receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    @Override
    public String toString() {
        return "SubmitOrderVM{" +
            "sell=" + sell +
            ", lease=" + lease +
            ", userId=" + userId +
            ", payPrice='" + payPrice + '\'' +
            ", openid='" + openid + '\'' +
            ", receiveAddressId=" + receiveAddressId +
            ", payNo='" + payNo + '\'' +
            ", shoppingProductIdList=" + shoppingProductIdList +
            '}';
    }
}
