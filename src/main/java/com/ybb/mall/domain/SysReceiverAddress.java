package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

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

    @Column(name = "openid")
    private String openid;

    @Column(name = "status")
    private Integer status;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToOne
    @JsonIgnoreProperties("receiveAddresses")
    private SysUser user;

    @ManyToMany
    @JoinTable(name = "sys_receiver_address_product",
               joinColumns = @JoinColumn(name = "sys_receiver_addresses_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"))
    private Set<SysOrder> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public SysReceiverAddress openid(String openid) {
        this.openid = openid;
        return this;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public Set<SysOrder> getProducts() {
        return products;
    }

    public SysReceiverAddress products(Set<SysOrder> sysOrders) {
        this.products = sysOrders;
        return this;
    }

    public SysReceiverAddress addProduct(SysOrder sysOrder) {
        this.products.add(sysOrder);
        sysOrder.getReceiveAddresses().add(this);
        return this;
    }

    public SysReceiverAddress removeProduct(SysOrder sysOrder) {
        this.products.remove(sysOrder);
        sysOrder.getReceiveAddresses().remove(this);
        return this;
    }

    public void setProducts(Set<SysOrder> sysOrders) {
        this.products = sysOrders;
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
            ", openid='" + getOpenid() + "'" +
            ", status=" + getStatus() +
            ", address='" + getAddress() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
