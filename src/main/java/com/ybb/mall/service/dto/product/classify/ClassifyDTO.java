package com.ybb.mall.service.dto.product.classify;

import com.ybb.mall.domain.SysProduct;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 商品分类信息
 * @Author 黄志成
 * @Date 2019-04-22
 * @Version
 */

public class ClassifyDTO {

    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类类型（0：出售 1：租赁）
     */
    private Integer type;

    /**
     * 排序等级
     */
    private Integer sort;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private List<SysProduct> products = new ArrayList<>();

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public List<SysProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SysProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ClassifyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            ", sort=" + sort +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", products=" + products +
            '}';
    }
}
