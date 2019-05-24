package com.ybb.mall.web.rest.controller.wx.vm.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description : 租赁商品
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

public class LeaseVM {
    private List<LeaseProductVM> productList;
    private Integer number;
    private BigDecimal totalPrice;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LeaseProductVM> getProductList() {
        return productList;
    }

    public void setProductList(List<LeaseProductVM> productList) {
        this.productList = productList;
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
        return "LeaseVM{" +
            "productList=" + productList +
            ", number=" + number +
            ", totalPrice=" + totalPrice +
            ", description='" + description + '\'' +
            '}';
    }
}
