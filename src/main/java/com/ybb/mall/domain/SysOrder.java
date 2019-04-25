package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
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

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 支付单号
     */
    @ApiModelProperty(value = "支付单号")
    @Column(name = "pay_no")
    private String payNO;

    /**
     * 订单金额
     */
    @ApiModelProperty(value = "订单金额")
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    @Column(name = "jhi_type")
    private Integer type;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    @Column(name = "pay_type")
    private Integer payType;

    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    @Column(name = "status")
    private Integer status;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    @Column(name = "jhi_number")
    private Integer number;

    /**
     * 订单描述
     */
    @ApiModelProperty(value = "订单描述")
    @Lob
    @Column(name = "description")
    private String description;

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

    @ManyToOne
    @JsonIgnoreProperties("orders")
    private SysReceiverAddress receiverAddress;

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

    public String getPayNO() {
        return payNO;
    }

    public SysOrder payNO(String payNO) {
        this.payNO = payNO;
        return this;
    }

    public void setPayNO(String payNO) {
        this.payNO = payNO;
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

    public Integer getPayType() {
        return payType;
    }

    public SysOrder payType(Integer payType) {
        this.payType = payType;
        return this;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public SysOrder status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDescription() {
        return description;
    }

    public SysOrder description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public SysReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public SysOrder receiverAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiverAddress = sysReceiverAddress;
        return this;
    }

    public void setReceiverAddress(SysReceiverAddress sysReceiverAddress) {
        this.receiverAddress = sysReceiverAddress;
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
            ", payNO='" + getPayNO() + "'" +
            ", price=" + getPrice() +
            ", type=" + getType() +
            ", payType=" + getPayType() +
            ", status=" + getStatus() +
            ", number=" + getNumber() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
