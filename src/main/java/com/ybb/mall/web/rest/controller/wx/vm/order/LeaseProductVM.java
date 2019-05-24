package com.ybb.mall.web.rest.controller.wx.vm.order;

/**
 * @Description : 租赁商品信息
 * @Author 黄志成
 * @Date 2019-05-23
 * @Version
 */

public class LeaseProductVM {
    private Long productId;
    private Integer productNumber;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    @Override
    public String toString() {
        return "LeaseProductVM{" +
            "productId=" + productId +
            ", productNumber=" + productNumber +
            '}';
    }
}
