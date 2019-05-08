package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 优惠券商品分类关联表
 */
@ApiModel(description = "优惠券商品分类关联表")
@Entity
@Table(name = "sys_coupon_classify")
public class SysCouponClassify implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonIgnoreProperties("couponClassifies")
    private SysCoupon coupon;

    @ManyToOne
    @JsonIgnoreProperties("")
    private SysClassify classify;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysCouponClassify createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysCouponClassify updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysCoupon getCoupon() {
        return coupon;
    }

    public SysCouponClassify coupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
        return this;
    }

    public void setCoupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
    }

    public SysClassify getClassify() {
        return classify;
    }

    public SysCouponClassify classify(SysClassify sysClassify) {
        this.classify = sysClassify;
        return this;
    }

    public void setClassify(SysClassify sysClassify) {
        this.classify = sysClassify;
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
        SysCouponClassify sysCouponClassify = (SysCouponClassify) o;
        if (sysCouponClassify.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCouponClassify.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCouponClassify{" +
            "id=" + getId() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
