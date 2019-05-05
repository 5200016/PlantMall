package com.ybb.mall.service.dto.sysdto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysAppointment entity.
 */
public class SysAppointmentDTO implements Serializable {

    private Long id;

    private ZonedDateTime time;

    @Lob
    private String remark;

    private Integer status;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long receiverAddressId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long sysReceiverAddressId) {
        this.receiverAddressId = sysReceiverAddressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysAppointmentDTO sysAppointmentDTO = (SysAppointmentDTO) o;
        if (sysAppointmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysAppointmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysAppointmentDTO{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", receiverAddress=" + getReceiverAddressId() +
            "}";
    }
}
