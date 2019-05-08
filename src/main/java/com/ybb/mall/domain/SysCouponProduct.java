package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 优惠券商品关联表
 */
@ApiModel(description = "优惠券商品关联表")
@Entity
@Table(name = "sys_coupon_product")
public class SysCouponProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 剩余数量
     */
    @ApiModelProperty(value = "剩余数量")
    @Column(name = "residue")
    private Integer residue;

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
    @JsonIgnoreProperties("couponProducts")
    private SysCoupon coupon;

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

    public Integer getResidue() {
        return residue;
    }

    public SysCouponProduct residue(Integer residue) {
        this.residue = residue;
        return this;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysCouponProduct createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysCouponProduct updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysCoupon getCoupon() {
        return coupon;
    }

    public SysCouponProduct coupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
        return this;
    }

    public void setCoupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
    }

    public SysProduct getProduct() {
        return product;
    }

    public SysCouponProduct product(SysProduct sysProduct) {
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
        SysCouponProduct sysCouponProduct = (SysCouponProduct) o;
        if (sysCouponProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCouponProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCouponProduct{" +
            "id=" + getId() +
            ", residue=" + getResidue() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
