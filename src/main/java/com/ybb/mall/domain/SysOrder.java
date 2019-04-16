package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 订单表
 */
@ApiModel(description = "订单表")
@Entity
@Table(name = "sys_order")
public class SysOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trade_no")
    private String tradeNo;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private SysUser user;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private SysProduct product;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<SysReceiverAddress> receiveAddresses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public SysOrder tradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SysOrder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public SysOrder type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public SysOrder number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysOrder createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysOrder updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysUser getUser() {
        return user;
    }

    public SysOrder user(SysUser sysUser) {
        this.user = sysUser;
        return this;
    }

    public void setUser(SysUser sysUser) {
        this.user = sysUser;
    }

    public SysProduct getProduct() {
        return product;
    }

    public SysOrder product(SysProduct sysProduct) {
        this.product = sysProduct;
        return this;
    }

    public void setProduct(SysProduct sysProduct) {
        this.product = sysProduct;
    }

    public Set<SysReceiverAddress> getReceiveAddresses() {
        return receiveAddresses;
    }

    public SysOrder receiveAddresses(Set<SysReceiverAddress> sysReceiverAddresses) {
        this.receiveAddresses = sysReceiverAddresses;
        return this;
    }

    public SysOrder addReceiveAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiveAddresses.add(sysReceiverAddress);
        sysReceiverAddress.getProducts().add(this);
        return this;
    }

    public SysOrder removeReceiveAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiveAddresses.remove(sysReceiverAddress);
        sysReceiverAddress.getProducts().remove(this);
        return this;
    }

    public void setReceiveAddresses(Set<SysReceiverAddress> sysReceiverAddresses) {
        this.receiveAddresses = sysReceiverAddresses;
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
        SysOrder sysOrder = (SysOrder) o;
        if (sysOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysOrder{" +
            "id=" + getId() +
            ", tradeNo='" + getTradeNo() + "'" +
            ", price=" + getPrice() +
            ", type=" + getType() +
            ", number=" + getNumber() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
