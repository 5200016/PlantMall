package com.ybb.mall.web.rest.controller.wx.vm.order;

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
    private String payNo;
    private Long userId;
    private Long receiveAddressId;

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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
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
            ", payNo='" + payNo + '\'' +
            ", userId=" + userId +
            ", receiveAddressId=" + receiveAddressId +
            '}';
    }
}
