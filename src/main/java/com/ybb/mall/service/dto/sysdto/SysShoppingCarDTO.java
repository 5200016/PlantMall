package com.ybb.mall.service.dto.sysdto;

import com.ybb.mall.domain.SysShoppingProduct;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the SysShoppingCar entity.
 */
public class SysShoppingCarDTO implements Serializable {

    private Long id;

    private Integer type;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Set<SysShoppingProduct> shoppingProducts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Set<SysShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public void setShoppingProducts(Set<SysShoppingProduct> shoppingProducts) {
        this.shoppingProducts = shoppingProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysShoppingCarDTO sysShoppingCarDTO = (SysShoppingCarDTO) o;
        if (sysShoppingCarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysShoppingCarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysShoppingCarDTO{" +
            "id=" + id +
            ", type=" + type +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", shoppingProducts=" + shoppingProducts +
            '}';
    }
}
