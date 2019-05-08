package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 优惠券表
 */
@ApiModel(description = "优惠券表")
@Entity
@Table(name = "sys_coupon")
public class SysCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 优惠券类型（0：商品优惠券 1：商品通用券 2：租赁优惠券）
     */
    @ApiModelProperty(value = "优惠券类型（0：商品优惠券 1：商品通用券 2：租赁优惠券）")
    @Column(name = "jhi_type")
    private Integer type;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    @Column(name = "name")
    private String name;

    /**
     * 优惠券面值
     */
    @ApiModelProperty(value = "优惠券面值")
    @Column(name = "jhi_value", precision = 10, scale = 2)
    private BigDecimal value;

    /**
     * 发放数量
     */
    @ApiModelProperty(value = "发放数量")
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 已领张数
     */
    @ApiModelProperty(value = "已领张数")
    @Column(name = "get_number")
    private Integer getNumber;

    /**
     * 限领数量
     */
    @ApiModelProperty(value = "限领数量")
    @Column(name = "jhi_limit")
    private Integer limit;

    /**
     * 有效期（起始日期）
     */
    @ApiModelProperty(value = "有效期（起始日期）")
    @Column(name = "start_time")
    private ZonedDateTime startTime;

    /**
     * 有效期（截止日期）
     */
    @ApiModelProperty(value = "有效期（截止日期）")
    @Column(name = "end_time")
    private ZonedDateTime endTime;

    /**
     * 使用描述
     */
    @ApiModelProperty(value = "使用描述")
    @Lob
    @Column(name = "description")
    private String description;

    /**
     * 满减
     */
    @ApiModelProperty(value = "满减")
    @Column(name = "money_off", precision = 10, scale = 2)
    private BigDecimal moneyOff;

    /**
     * 适用范围（0：指定商品 1：分类商品）
     */
    @ApiModelProperty(value = "适用范围（0：指定商品 1：分类商品）")
    @Column(name = "jhi_range")
    private Integer range;

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

    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "coupon")
    private Set<SysCouponUser> couponUsers = new HashSet<>();
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "coupon")
    private Set<SysCouponProduct> couponProducts = new HashSet<>();
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "coupon")
    private Set<SysCouponClassify> couponClassifies = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public SysCoupon type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public SysCoupon name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public SysCoupon value(BigDecimal value) {
        this.value = value;
        return this;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public SysCoupon quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getGetNumber() {
        return getNumber;
    }

    public SysCoupon getNumber(Integer getNumber) {
        this.getNumber = getNumber;
        return this;
    }

    public void setGetNumber(Integer getNumber) {
        this.getNumber = getNumber;
    }

    public Integer getLimit() {
        return limit;
    }

    public SysCoupon limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public SysCoupon startTime(ZonedDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public SysCoupon endTime(ZonedDateTime endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public SysCoupon description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoneyOff() {
        return moneyOff;
    }

    public SysCoupon moneyOff(BigDecimal moneyOff) {
        this.moneyOff = moneyOff;
        return this;
    }

    public void setMoneyOff(BigDecimal moneyOff) {
        this.moneyOff = moneyOff;
    }

    public Integer getRange() {
        return range;
    }

    public SysCoupon range(Integer range) {
        this.range = range;
        return this;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysCoupon createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysCoupon updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysCouponUser> getCouponUsers() {
        return couponUsers;
    }

    public SysCoupon couponUsers(Set<SysCouponUser> sysCouponUsers) {
        this.couponUsers = sysCouponUsers;
        return this;
    }

    public SysCoupon addCouponUser(SysCouponUser sysCouponUser) {
        this.couponUsers.add(sysCouponUser);
        sysCouponUser.setCoupon(this);
        return this;
    }

    public SysCoupon removeCouponUser(SysCouponUser sysCouponUser) {
        this.couponUsers.remove(sysCouponUser);
        sysCouponUser.setCoupon(null);
        return this;
    }

    public void setCouponUsers(Set<SysCouponUser> sysCouponUsers) {
        this.couponUsers = sysCouponUsers;
    }

    public Set<SysCouponProduct> getCouponProducts() {
        return couponProducts;
    }

    public SysCoupon couponProducts(Set<SysCouponProduct> sysCouponProducts) {
        this.couponProducts = sysCouponProducts;
        return this;
    }

    public SysCoupon addCouponProduct(SysCouponProduct sysCouponProduct) {
        this.couponProducts.add(sysCouponProduct);
        sysCouponProduct.setCoupon(this);
        return this;
    }

    public SysCoupon removeCouponProduct(SysCouponProduct sysCouponProduct) {
        this.couponProducts.remove(sysCouponProduct);
        sysCouponProduct.setCoupon(null);
        return this;
    }

    public void setCouponProducts(Set<SysCouponProduct> sysCouponProducts) {
        this.couponProducts = sysCouponProducts;
    }

    public Set<SysCouponClassify> getCouponClassifies() {
        return couponClassifies;
    }

    public SysCoupon couponClassifies(Set<SysCouponClassify> sysCouponClassifies) {
        this.couponClassifies = sysCouponClassifies;
        return this;
    }

    public SysCoupon addCouponClassify(SysCouponClassify sysCouponClassify) {
        this.couponClassifies.add(sysCouponClassify);
        sysCouponClassify.setCoupon(this);
        return this;
    }

    public SysCoupon removeCouponClassify(SysCouponClassify sysCouponClassify) {
        this.couponClassifies.remove(sysCouponClassify);
        sysCouponClassify.setCoupon(null);
        return this;
    }

    public void setCouponClassifies(Set<SysCouponClassify> sysCouponClassifies) {
        this.couponClassifies = sysCouponClassifies;
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
        SysCoupon sysCoupon = (SysCoupon) o;
        if (sysCoupon.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCoupon.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCoupon{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", name='" + getName() + "'" +
            ", value=" + getValue() +
            ", quantity=" + getQuantity() +
            ", getNumber=" + getGetNumber() +
            ", limit=" + getLimit() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", moneyOff=" + getMoneyOff() +
            ", range=" + getRange() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
