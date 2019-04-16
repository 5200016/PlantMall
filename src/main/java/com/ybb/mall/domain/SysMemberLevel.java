package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 会员等级表
 */
@ApiModel(description = "会员等级表")
@Entity
@Table(name = "sys_member_level")
public class SysMemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_level")
    private Integer level;

    @Column(name = "left_value")
    private Integer leftValue;

    @Column(name = "right_value")
    private Integer rightValue;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @OneToMany(mappedBy = "memberLevel")
    private Set<SysUser> users = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SysMemberLevel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public SysMemberLevel level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLeftValue() {
        return leftValue;
    }

    public SysMemberLevel leftValue(Integer leftValue) {
        this.leftValue = leftValue;
        return this;
    }

    public void setLeftValue(Integer leftValue) {
        this.leftValue = leftValue;
    }

    public Integer getRightValue() {
        return rightValue;
    }

    public SysMemberLevel rightValue(Integer rightValue) {
        this.rightValue = rightValue;
        return this;
    }

    public void setRightValue(Integer rightValue) {
        this.rightValue = rightValue;
    }

    public Double getDiscount() {
        return discount;
    }

    public SysMemberLevel discount(Double discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysMemberLevel createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysMemberLevel updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysUser> getUsers() {
        return users;
    }

    public SysMemberLevel users(Set<SysUser> sysUsers) {
        this.users = sysUsers;
        return this;
    }

    public SysMemberLevel addUser(SysUser sysUser) {
        this.users.add(sysUser);
        sysUser.setMemberLevel(this);
        return this;
    }

    public SysMemberLevel removeUser(SysUser sysUser) {
        this.users.remove(sysUser);
        sysUser.setMemberLevel(null);
        return this;
    }

    public void setUsers(Set<SysUser> sysUsers) {
        this.users = sysUsers;
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
        SysMemberLevel sysMemberLevel = (SysMemberLevel) o;
        if (sysMemberLevel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMemberLevel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMemberLevel{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", leftValue=" + getLeftValue() +
            ", rightValue=" + getRightValue() +
            ", discount=" + getDiscount() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
