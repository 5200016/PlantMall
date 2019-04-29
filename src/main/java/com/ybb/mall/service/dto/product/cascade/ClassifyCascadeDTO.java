package com.ybb.mall.service.dto.product.cascade;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 商品分类级联
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public class ClassifyCascadeDTO {
    private Long value;

    private String label;

    private List<ProductCascadeDTO> children = new ArrayList<>();

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

    public List<ProductCascadeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCascadeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ClassifyCascadeDTO{" +
            "value=" + value +
            ", label='" + label + '\'' +
            ", children=" + children +
            '}';
    }
}
