package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 预约表
 */
@ApiModel(description = "预约表")
@Entity
@Table(name = "sys_appointment")
public class SysAppointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 预约时间
     */
    @ApiModelProperty(value = "预约时间")
    @Column(name = "jhi_time")
    private ZonedDateTime time;

    /**
     * 预约备注
     */
    @ApiModelProperty(value = "预约备注")
    @Lob
    @Column(name = "remark")
    private String remark;

    /**
     * 预约状态（0：未处理 1：已处理）
     */
    @ApiModelProperty(value = "预约状态（0：未处理 1：已处理）")
    @Column(name = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_time")
    private ZonedDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    /**
     * 预约关联收货地址
     */
    @ApiModelProperty(value = "预约关联收货地址")
    @ManyToOne
    @JsonIgnoreProperties("")
    private SysReceiverAddress receiverAddress;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public SysAppointment time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public SysAppointment remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public SysAppointment status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysAppointment createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysAppointment updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysReceiverAddress getReceiverAddress() {
        return receiverAddress;
    }

    public SysAppointment receiverAddress(SysReceiverAddress sysReceiverAddress) {
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
        SysAppointment sysAppointment = (SysAppointment) o;
        if (sysAppointment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysAppointment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysAppointment{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", remark='" + getRemark() + "'" +
            ", status=" + getStatus() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
