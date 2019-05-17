package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 收货地址表
 */
@ApiModel(description = "收货地址表")
@Entity
@Table(name = "sys_receiver_address")
public class SysReceiverAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    @Column(name = "name")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

    /**
     * 收货地区
     */
    @ApiModelProperty(value = "收货地区")
    @Column(name = "area")
    private String area;

    /**
     * 收货详细地址
     */
    @ApiModelProperty(value = "收货详细地址")
    @Lob
    @Column(name = "address")
    private String address;

    /**
     * 是否为默认地址（0：否 1：是）
     */
    @ApiModelProperty(value = "是否为默认地址（0：否 1：是）")
    @Column(name = "status")
    private Integer status;

    /**
     * 是否激活（false：否 true：是）
     */
    @ApiModelProperty(value = "是否激活（false：否 true：是）")
    @Column(name = "active")
    private Boolean active;

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
    @JsonIgnoreProperties("receiverAddresses")
    private SysUser user;

    @OneToMany(mappedBy = "receiverAddress")
    private Set<SysOrder> orders = new HashSet<>();
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

    public SysReceiverAddress name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public SysReceiverAddress phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public SysReceiverAddress area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public SysReceiverAddress address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public SysReceiverAddress status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean isActive() {
        return active;
    }

    public SysReceiverAddress active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysReceiverAddress createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysReceiverAddress updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysUser getUser() {
        return user;
    }

    public SysReceiverAddress user(SysUser sysUser) {
        this.user = sysUser;
        return this;
    }

    public void setUser(SysUser sysUser) {
        this.user = sysUser;
    }

    public Set<SysOrder> getOrders() {
        return orders;
    }

    public SysReceiverAddress orders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
        return this;
    }

    public SysReceiverAddress addOrder(SysOrder sysOrder) {
        this.orders.add(sysOrder);
        sysOrder.setReceiverAddress(this);
        return this;
    }

    public SysReceiverAddress removeOrder(SysOrder sysOrder) {
        this.orders.remove(sysOrder);
        sysOrder.setReceiverAddress(null);
        return this;
    }

    public void setOrders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
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
        SysReceiverAddress sysReceiverAddress = (SysReceiverAddress) o;
        if (sysReceiverAddress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysReceiverAddress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysReceiverAddress{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", area='" + getArea() + "'" +
            ", address='" + getAddress() + "'" +
            ", status=" + getStatus() +
            ", active='" + isActive() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
