package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 养护人员表
 */
@ApiModel(description = "养护人员表")
@Entity
@Table(name = "sys_maintenance_personnel")
public class SysMaintenancePersonnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    @Column(name = "openid")
    private String openid;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Column(name = "name")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @Column(name = "phone")
    private String phone;

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

    @OneToMany(mappedBy = "maintenancePersonnel")
    private Set<SysOrder> orders = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public SysMaintenancePersonnel openid(String openid) {
        this.openid = openid;
        return this;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public SysMaintenancePersonnel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public SysMaintenancePersonnel phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysMaintenancePersonnel createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysMaintenancePersonnel updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysOrder> getOrders() {
        return orders;
    }

    public SysMaintenancePersonnel orders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
        return this;
    }

    public SysMaintenancePersonnel addOrder(SysOrder sysOrder) {
        this.orders.add(sysOrder);
        sysOrder.setMaintenancePersonnel(this);
        return this;
    }

    public SysMaintenancePersonnel removeOrder(SysOrder sysOrder) {
        this.orders.remove(sysOrder);
        sysOrder.setMaintenancePersonnel(null);
        return this;
    }

    public void setOrders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
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
        SysMaintenancePersonnel sysMaintenancePersonnel = (SysMaintenancePersonnel) o;
        if (sysMaintenancePersonnel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMaintenancePersonnel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMaintenancePersonnel{" +
            "id=" + getId() +
            ", openid='" + getOpenid() + "'" +
            ", name='" + getName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
