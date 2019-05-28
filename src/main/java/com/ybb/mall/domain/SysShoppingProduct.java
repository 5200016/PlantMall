package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 购物车商品关联表
 */
@ApiModel(description = "购物车商品关联表")
@Entity
@Table(name = "sys_shopping_product")
public class SysShoppingProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @Column(name = "product_number")
    private Integer productNumber;

    /**
     * 商品类型（0：出售商品 1：租赁商品）
     */
    @ApiModelProperty(value = "商品类型（0：出售商品 1：租赁商品）")
    @Column(name = "product_type")
    private Integer productType;

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
    @JsonIgnoreProperties("shoppingProducts")
    private SysShoppingCar shoppingCar;

    @ManyToOne
    @JsonIgnoreProperties("shoppingProducts")
    private SysProduct product;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public SysShoppingProduct productNumber(Integer productNumber) {
        this.productNumber = productNumber;
        return this;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public Integer getProductType() {
        return productType;
    }

    public SysShoppingProduct productType(Integer productType) {
        this.productType = productType;
        return this;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysShoppingProduct createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysShoppingProduct updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysShoppingCar getShoppingCar() {
        return shoppingCar;
    }

    public SysShoppingProduct shoppingCar(SysShoppingCar sysShoppingCar) {
        this.shoppingCar = sysShoppingCar;
        return this;
    }

    public void setShoppingCar(SysShoppingCar sysShoppingCar) {
        this.shoppingCar = sysShoppingCar;
    }

    public SysProduct getProduct() {
        return product;
    }

    public SysShoppingProduct product(SysProduct sysProduct) {
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
        SysShoppingProduct sysShoppingProduct = (SysShoppingProduct) o;
        if (sysShoppingProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysShoppingProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysShoppingProduct{" +
            "id=" + getId() +
            ", productNumber=" + getProductNumber() +
            ", productType=" + getProductType() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
