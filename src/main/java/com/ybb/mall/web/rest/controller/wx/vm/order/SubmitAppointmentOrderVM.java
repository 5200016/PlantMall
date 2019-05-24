package com.ybb.mall.web.rest.controller.wx.vm.order;

/**
 * @Description : 提交预约订单
 * @Author 黄志成
 * @Date 2019-05-22
 * @Version
 */

public class SubmitAppointmentOrderVM {
    private String time;
    private String remark;
    private Long receiverAddressId;
    private Long userId;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SubmitAppointmentOrderVM{" +
            "time='" + time + '\'' +
            ", remark='" + remark + '\'' +
            ", receiverAddressId=" + receiverAddressId +
            ", userId=" + userId +
            '}';
    }
}
