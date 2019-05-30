package com.ybb.mall.web.rest.controller.wx.vm.order;

/**
 * @Description : 商品数量加减运算
 * @Author 黄志成
 * @Date 2019-05-28
 * @Version
 */

public class ProductOperationVM {
    private Long userId;
    private Long productId;
    private Integer productType;
    private Integer number;
    private Integer type;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductOperationVM{" +
            "userId=" + userId +
            ", productId=" + productId +
            ", productType=" + productType +
            ", number=" + number +
            ", type=" + type +
            '}';
    }
}
