package com.ybb.mall.web.rest.vm.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : 订单商品
 * @Author 黄志成
 * @Date 2019-07-12
 * @Version
 */

@ApiModel(description = "订单商品")
public class OrderProductVM {

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long productId;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
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
        return "OrderProductVM{" +
            "productId=" + productId +
            ", productNumber=" + productNumber +
            '}';
    }
}
