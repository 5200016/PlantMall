package com.ybb.mall.web.rest.controller.wx.vm.review;

import java.util.List;

/**
 * @Description : 商品评论
 * @Author 黄志成
 * @Date 2019-06-10
 * @Version
 */

public class ReviewBriefVM {
    private Long orderId;
    private Long userId;
    private List<DataVM> data;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<DataVM> getData() {
        return data;
    }

    public void setData(List<DataVM> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ReviewBriefVM{" +
            "orderId=" + orderId +
            ", userId=" + userId +
            ", data=" + data +
            '}';
    }
}
