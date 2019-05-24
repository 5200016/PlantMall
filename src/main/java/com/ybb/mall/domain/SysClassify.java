package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 商品分类表
 */
@ApiModel(description = "商品分类表")
@Entity
@Table(name = "sys_classify")
public class SysClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    @Column(name = "name")
    private String name;

    /**
     * 分类类型（0：出售 1：租赁）
     */
    @ApiModelProperty(value = "分类类型（0：出售 1：租赁）")
    @Column(name = "jhi_type")
    private Integer type;

    /**
     * 排序等级
     */
    @ApiModelProperty(value = "排序等级")
    @Column(name = "jhi_sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private ZonedDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToMany(mappedBy = "classifies")
    @JsonIgnore
    private Set<SysProduct> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SysClassify name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public SysClassify type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public SysClassify sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysClassify createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysClassify updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysProduct> getProducts() {
        return products;
    }

    public SysClassify products(Set<SysProduct> sysProducts) {
        this.products = sysProducts;
        return this;
    }

    public SysClassify addProduct(SysProduct sysProduct) {
        this.products.add(sysProduct);
        sysProduct.getClassifies().add(this);
        return this;
    }

    public SysClassify removeProduct(SysProduct sysProduct) {
        this.products.remove(sysProduct);
        sysProduct.getClassifies().remove(this);
        return this;
    }

    public void setProducts(Set<SysProduct> sysProducts) {
        this.products = sysProducts;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysClassify sysClassify = (SysClassify) o;
        if (sysClassify.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysClassify.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysClassify{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type=" + getType() +
            ", sort=" + getSort() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
