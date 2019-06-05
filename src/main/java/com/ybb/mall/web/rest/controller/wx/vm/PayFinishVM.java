package com.ybb.mall.web.rest.controller.wx.vm;

import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-05-31
 * @Version
 */

public class PayFinishVM {
    private Long userId;
    private String tradeNo;
    private List<Long> shoppingProductIdList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public List<Long> getShoppingProductIdList() {
        return shoppingProductIdList;
    }

    public void setShoppingProductIdList(List<Long> shoppingProductIdList) {
        this.shoppingProductIdList = shoppingProductIdList;
    }

    @Override
    public String toString() {
        return "PayFinishVM{" +
            "userId=" + userId +
            ", tradeNo='" + tradeNo + '\'' +
            ", shoppingProductIdList=" + shoppingProductIdList +
            '}';
    }
}
