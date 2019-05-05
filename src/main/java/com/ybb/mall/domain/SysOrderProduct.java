package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 订单商品关联表
 */
@ApiModel(description = "订单商品关联表")
@Entity
@Table(name = "sys_order_product")
public class SysOrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品状态（0：无状态 1：已清除 2：补加）
     */
    @ApiModelProperty(value = "商品状态（0：无状态 1：已清除 2：补加）")
    @Column(name = "product_status")
    private Integer productStatus;

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

    @ManyToOne
    @JsonIgnoreProperties("orderProducts")
    private SysOrder order;

    @ManyToOne
    @JsonIgnoreProperties("")
    private SysProduct product;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public SysOrderProduct productStatus(Integer productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysOrderProduct createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysOrderProduct updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysOrder getOrder() {
        return order;
    }

    public SysOrderProduct order(SysOrder sysOrder) {
        this.order = sysOrder;
        return this;
    }

    public void setOrder(SysOrder sysOrder) {
        this.order = sysOrder;
    }

    public SysProduct getProduct() {
        return product;
    }

    public SysOrderProduct product(SysProduct sysProduct) {
        this.product = sysProduct;
        return this;
    }

    public void setProduct(SysProduct sysProduct) {
        this.product = sysProduct;
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
        SysOrderProduct sysOrderProduct = (SysOrderProduct) o;
        if (sysOrderProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysOrderProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysOrderProduct{" +
            "id=" + getId() +
            ", productStatus=" + getProductStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
