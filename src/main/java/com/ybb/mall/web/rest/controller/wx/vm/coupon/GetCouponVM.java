package com.ybb.mall.web.rest.controller.wx.vm.coupon;

/**
 * @Description : 领取优惠券
 * @Author 黄志成
 * @Date 2019-06-12
 * @Version
 */

public class GetCouponVM {
    private Long userId;
    private Long couponId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    @Override
    public String toString() {
        return "GetCouponVM{" +
            "userId=" + userId +
            ", couponId=" + couponId +
            '}';
    }
}
