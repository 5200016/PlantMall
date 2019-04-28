package com.ybb.mall.service.dto.order;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.domain.SysUser;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @Description : 订单列表
 * @Author 黄志成
 * @Date 2019-04-25
 * @Version
 */

public class OrderListDTO {
    private Long id;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 支付单号
     */
    private String payNo;

    /**
     * 订单金额
     */
    private BigDecimal price;

    /**
     * 订单类型
     */
    private Integer type;

    /**
     * 支付类型（0：线上支付，1：余额支付）
     */
    private Integer payType;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 订单描述
     */
    private String description;

    /**
     * 养护计划状态（0：未设置，1：已设置）
     */
    private Integer maintenancePlanStatus;


    private ZonedDateTime createTime;

    private SysUser user;

    private SysProduct product;

    private SysReceiverAddress receiverAddress;

    public OrderListDTO(Long id, String tradeNo, String payNo, BigDecimal price, Integer type, Integer payType, Integer status, Integer number, String description, Integer maintenancePlanStatus, ZonedDateTime createTime, SysUser user, SysProduct product, SysReceiverAddress receiverAddress) {
        this.id = id;
        this.tradeNo = tradeNo;
        this.payNo = payNo;
        this.price = price;
        this.type = type;
        this.payType = payType;
        this.status = status;
        this.number = number;
        this.description = description;
        this.maintenancePlanStatus = maintenancePlanStatus;
        this.createTime = createTime;
        this.user = user;
        this.product = product;
        this.receiverAddress = receiverAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaintenancePlanStatus() {
        return maintenancePlanStatus;
    }

    public void setMaintenancePlanStatus(Integer maintenancePlanStatus) {
        this.maintenancePlanStatus = maintenancePlanStatus;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysProduct getProduct() {
        return product;
    }

    public void setProduct(SysProduct product) {
        this.product = product;
    }

    public SysReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(SysReceiverAddress receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    @Override
    public String toString() {
        return "OrderListDTO{" +
            "id=" + id +
            ", tradeNo='" + tradeNo + '\'' +
            ", payNo='" + payNo + '\'' +
            ", price=" + price +
            ", type=" + type +
            ", payType=" + payType +
            ", status=" + status +
            ", number=" + number +
            ", description='" + description + '\'' +
            ", maintenancePlanStatus=" + maintenancePlanStatus +
            ", createTime=" + createTime +
            ", user=" + user +
            ", product=" + product +
            ", receiverAddress=" + receiverAddress +
            '}';
    }
}
