package com.ybb.mall.web.rest.controller.wx.vm.order;

import java.math.BigDecimal;

/**
 * @Description : 出售商品
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

public class SellVM {
    private Long productId;
    private Integer number;
    private BigDecimal totalPrice;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SellVM{" +
            "productId=" + productId +
            ", number=" + number +
            ", totalPrice=" + totalPrice +
            ", description='" + description + '\'' +
            '}';
    }
}
