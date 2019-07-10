package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 养护任务完成表
 */
@ApiModel(description = "养护任务完成表")
@Entity
@Table(name = "sys_maintenance_finish")
public class SysMaintenanceFinish implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 计划时间
     */
    @ApiModelProperty(value = "计划时间")
    @Column(name = "jhi_time")
    private String time;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @Column(name = "finish_time")
    private String finishTime;

    /**
     * 照片路径
     */
    @ApiModelProperty(value = "照片路径")
    @Column(name = "url")
    private String url;

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

    @ManyToOne
    @JsonIgnoreProperties("maintenanceFinishes")
    private SysOrder order;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public SysMaintenanceFinish time(String time) {
        this.time = time;
        return this;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public SysMaintenanceFinish finishTime(String finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getUrl() {
        return url;
    }

    public SysMaintenanceFinish url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysMaintenanceFinish createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysMaintenanceFinish updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysOrder getOrder() {
        return order;
    }

    public SysMaintenanceFinish order(SysOrder sysOrder) {
        this.order = sysOrder;
        return this;
    }

    public void setOrder(SysOrder sysOrder) {
        this.order = sysOrder;
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
        SysMaintenanceFinish sysMaintenanceFinish = (SysMaintenanceFinish) o;
        if (sysMaintenanceFinish.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMaintenanceFinish.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMaintenanceFinish{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", finishTime='" + getFinishTime() + "'" +
            ", url='" + getUrl() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
