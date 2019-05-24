package com.ybb.mall.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 客服表
 */
@ApiModel(description = "客服表")
@Entity
@Table(name = "sys_customer_service")
public class SysCustomerService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 客服电话
     */
    @ApiModelProperty(value = "客服电话")
    @Column(name = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @Column(name = "email")
    private String email;

    /**
     * 公司地址
     */
    @ApiModelProperty(value = "公司地址")
    @Lob
    @Column(name = "address")
    private String address;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public SysCustomerService phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public SysCustomerService email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public SysCustomerService address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysCustomerService createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysCustomerService updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
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
        SysCustomerService sysCustomerService = (SysCustomerService) o;
        if (sysCustomerService.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysCustomerService.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysCustomerService{" +
            "id=" + getId() +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
