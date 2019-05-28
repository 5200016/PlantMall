package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description : 购物车商品
 * @Author 黄志成
 * @Date 2019-05-28
 * @Version
 */

public class ShoppingProductVM {
    private Integer type;
    private Long shoppingProductId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getShoppingProductId() {
        return shoppingProductId;
    }

    public void setShoppingProductId(Long shoppingProductId) {
        this.shoppingProductId = shoppingProductId;
    }

    @Override
    public String toString() {
        return "ShoppingProductVM{" +
            "type=" + type +
            ", shoppingProductId=" + shoppingProductId +
            '}';
    }
}
