package com.ybb.mall.service.dto.product.cascade;

/**
 * @Description : 商品级联
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public class ProductCascadeDTO {
    private Long value;

    private String label;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ProductCascadeDTO{" +
            "value=" + value +
            ", label='" + label + '\'' +
            '}';
    }
}
