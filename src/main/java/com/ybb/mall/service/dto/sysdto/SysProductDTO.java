package com.ybb.mall.service.dto.sysdto;

import javax.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the SysProduct entity.
 */
public class SysProductDTO implements Serializable {

    private Long id;

    private String name;

    private BigDecimal leasePrice;

    private BigDecimal price;

    private String image;

    private Integer inventory;

    private Integer sale;

    @Lob
    private String description;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Set<SysClassifyDTO> classifies = new HashSet<>();

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

    public BigDecimal getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<SysClassifyDTO> getClassifies() {
        return classifies;
    }

    public void setClassifies(Set<SysClassifyDTO> sysClassifies) {
        this.classifies = sysClassifies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysProductDTO sysProductDTO = (SysProductDTO) o;
        if (sysProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysProductDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", leasePrice=" + getLeasePrice() +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", inventory=" + getInventory() +
            ", sale=" + getSale() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
