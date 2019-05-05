package com.ybb.mall.service.dto.order;

import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.domain.SysUser;

import javax.persistence.Lob;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-29
 * @Version
 */

public class OrderDTO {
    private Long id;

    private String tradeNo;

    private String payNo;

    private BigDecimal price;

    private Integer type;

    private Integer payType;

    private Integer status;

    private Integer number;

    @Lob
    private String description;

    private Integer maintenancePlanStatus;

    private String maintenanceTime;

    private String maintenanceDescription;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long userId;

    private Long receiverAddressId;

    private Long maintenancePersonnelId;

    private SysUser user;

    private SysReceiverAddress receiverAddress;

    public String getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(String maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public String getMaintenanceDescription() {
        return maintenanceDescription;
    }

    public void setMaintenanceDescription(String maintenanceDescription) {
        this.maintenanceDescription = maintenanceDescription;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(SysReceiverAddress receiverAddress) {
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

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long sysUserId) {
        this.userId = sysUserId;
    }

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long sysReceiverAddressId) {
        this.receiverAddressId = sysReceiverAddressId;
    }

    public Long getMaintenancePersonnelId() {
        return maintenancePersonnelId;
    }

    public void setMaintenancePersonnelId(Long maintenancePersonnelId) {
        this.maintenancePersonnelId = maintenancePersonnelId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
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
            ", maintenanceTime='" + maintenanceTime + '\'' +
            ", maintenanceDescription='" + maintenanceDescription + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", userId=" + userId +
            ", receiverAddressId=" + receiverAddressId +
            ", maintenancePersonnelId=" + maintenancePersonnelId +
            ", user=" + user +
            ", receiverAddress=" + receiverAddress +
            '}';
    }
}
