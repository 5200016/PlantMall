package com.ybb.mall.service.dto.sysdto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysReceiverAddress entity.
 */
public class SysReceiverAddressDTO implements Serializable {

    private Long id;

    private String name;

    private String phone;

    private String area;

    @Lob
    private String address;

    private Integer status;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysReceiverAddressDTO sysReceiverAddressDTO = (SysReceiverAddressDTO) o;
        if (sysReceiverAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysReceiverAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysReceiverAddressDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", area='" + getArea() + "'" +
            ", address='" + getAddress() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
