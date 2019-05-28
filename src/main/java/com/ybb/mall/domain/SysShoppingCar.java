package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 购物车表
 */
@ApiModel(description = "购物车表")
@Entity
@Table(name = "sys_shopping_car")
public class SysShoppingCar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类型（0：出售商品 1：租赁商品）
     */
    @ApiModelProperty(value = "类型（0：出售商品 1：租赁商品）")
    @Column(name = "jhi_type")
    private Integer type;

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
    @JsonIgnoreProperties("shoppingCars")
    private SysUser user;

    @OneToMany(mappedBy = "shoppingCar")
    private Set<SysShoppingProduct> shoppingProducts = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public SysShoppingCar type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysShoppingCar createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysShoppingCar updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysUser getUser() {
        return user;
    }

    public SysShoppingCar user(SysUser sysUser) {
        this.user = sysUser;
        return this;
    }

    public void setUser(SysUser sysUser) {
        this.user = sysUser;
    }

    public Set<SysShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public SysShoppingCar shoppingProducts(Set<SysShoppingProduct> sysShoppingProducts) {
        this.shoppingProducts = sysShoppingProducts;
        return this;
    }

    public SysShoppingCar addShoppingProduct(SysShoppingProduct sysShoppingProduct) {
        this.shoppingProducts.add(sysShoppingProduct);
        sysShoppingProduct.setShoppingCar(this);
        return this;
    }

    public SysShoppingCar removeShoppingProduct(SysShoppingProduct sysShoppingProduct) {
        this.shoppingProducts.remove(sysShoppingProduct);
        sysShoppingProduct.setShoppingCar(null);
        return this;
    }

    public void setShoppingProducts(Set<SysShoppingProduct> sysShoppingProducts) {
        this.shoppingProducts = sysShoppingProducts;
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
        SysShoppingCar sysShoppingCar = (SysShoppingCar) o;
        if (sysShoppingCar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysShoppingCar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysShoppingCar{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
