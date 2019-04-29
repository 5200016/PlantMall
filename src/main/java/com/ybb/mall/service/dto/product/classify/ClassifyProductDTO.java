package com.ybb.mall.service.dto.product.classify;

import com.ybb.mall.domain.SysProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 分类，商品集合
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public class ClassifyProductDTO {

    private Long id;

    private String name;

    private List<SysProduct> products = new ArrayList<>();

    public ClassifyProductDTO(Long id, String name, List<SysProduct> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SysProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClassifyProductDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", products=" + products +
            '}';
    }
}
