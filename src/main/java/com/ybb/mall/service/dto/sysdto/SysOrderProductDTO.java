package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysOrderProduct entity.
 */
public class SysOrderProductDTO implements Serializable {

    private Long id;

    private Integer productStatus;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long orderId;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long sysOrderId) {
        this.orderId = sysOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long sysProductId) {
        this.productId = sysProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysOrderProductDTO sysOrderProductDTO = (SysOrderProductDTO) o;
        if (sysOrderProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysOrderProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysOrderProductDTO{" +
            "id=" + getId() +
            ", productStatus=" + getProductStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", order=" + getOrderId() +
            ", product=" + getProductId() +
            "}";
    }
}
