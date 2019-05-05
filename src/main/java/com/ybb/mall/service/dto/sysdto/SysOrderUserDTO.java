package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysOrderUser entity.
 */
public class SysOrderUserDTO implements Serializable {

    private Long id;

    private Boolean flag;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long userId;

    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long sysOrderId) {
        this.orderId = sysOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysOrderUserDTO sysOrderUserDTO = (SysOrderUserDTO) o;
        if (sysOrderUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysOrderUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysOrderUserDTO{" +
            "id=" + getId() +
            ", flag='" + isFlag() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", user=" + getUserId() +
            ", order=" + getOrderId() +
            "}";
    }
}
