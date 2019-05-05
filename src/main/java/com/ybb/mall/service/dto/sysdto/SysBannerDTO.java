package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysBanner entity.
 */
public class SysBannerDTO implements Serializable {

    private Long id;

    private String image;

    private String path;

    private Integer type;

    private Integer sort;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private Long productId;

    private Long classifyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long sysProductId) {
        this.productId = sysProductId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long sysClassifyId) {
        this.classifyId = sysClassifyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysBannerDTO sysBannerDTO = (SysBannerDTO) o;
        if (sysBannerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysBannerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysBannerDTO{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", path='" + getPath() + "'" +
            ", type=" + getType() +
            ", sort=" + getSort() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", product=" + getProductId() +
            ", classify=" + getClassifyId() +
            "}";
    }
}
