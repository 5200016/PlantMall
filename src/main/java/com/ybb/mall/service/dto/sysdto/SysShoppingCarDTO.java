package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysShoppingCar entity.
 */
public class SysShoppingCarDTO implements Serializable {

    private Long id;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long productId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        SysShoppingCarDTO sysShoppingCarDTO = (SysShoppingCarDTO) o;
        if (sysShoppingCarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysShoppingCarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysShoppingCarDTO{" +
            "id=" + getId() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", product=" + getProductId() +
            "}";
    }
}
