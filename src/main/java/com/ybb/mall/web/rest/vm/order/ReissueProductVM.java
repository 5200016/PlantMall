package com.ybb.mall.web.rest.vm.order;

/**
 * @Description : 补发订单商品
 * @Author 黄志成
 * @Date 2019-05-05
 * @Version
 */

public class ReissueProductVM {
    private Long productId;

    private Long orderId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "ReissueProductVM{" +
            "productId=" + productId +
            ", orderId=" + orderId +
            '}';
    }
}
