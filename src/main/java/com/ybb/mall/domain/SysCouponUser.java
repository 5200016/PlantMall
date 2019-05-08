package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 优惠券用户关联表
 */
@ApiModel(description = "优惠券用户关联表")
@Entity
@Table(name = "sys_coupon_user")
public class SysCouponUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 使用状态
     */
    @ApiModelProperty(value = "使用状态")
    @Column(name = "use_status")
    private Integer useStatus;

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
    @JsonIgnoreProperties("couponUsers")
    private SysUser user;

    @ManyToOne
    @JsonIgnoreProperties("couponUsers")
    private SysCoupon coupon;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public SysCouponUser useStatus(Integer useStatus) {
        this.useStatus = useStatus;
        return this;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysCouponUser createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysCouponUser updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysUser getUser() {
        return user;
    }

    public SysCouponUser user(SysUser sysUser) {
        this.user = sysUser;
        return this;
    }

    public void setUser(SysUser sysUser) {
        this.user = sysUser;
    }

    public SysCoupon getCoupon() {
        return coupon;
    }

    public SysCouponUser coupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
        return this;
    }

    public void setCoupon(SysCoupon sysCoupon) {
        this.coupon = sysCoupon;
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
        SysCouponUser sysCouponUser = (SysCouponUser) o;
        if (sysCouponUser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCouponUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCouponUser{" +
            "id=" + getId() +
            ", useStatus=" + getUseStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
