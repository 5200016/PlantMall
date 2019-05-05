package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysMaintenancePersonnel entity.
 */
public class SysMaintenancePersonnelDTO implements Serializable {

    private Long id;

    private String openid;

    private String name;

    private String phone;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysMaintenancePersonnelDTO sysMaintenancePersonnelDTO = (SysMaintenancePersonnelDTO) o;
        if (sysMaintenancePersonnelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMaintenancePersonnelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMaintenancePersonnelDTO{" +
            "id=" + getId() +
            ", openid='" + getOpenid() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
