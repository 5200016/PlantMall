package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 广告图片表
 */
@ApiModel(description = "广告图片表")
@Entity
@Table(name = "sys_banner")
public class SysBanner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    @Column(name = "image")
    private String image;

    /**
     * 图片跳转路径（微信小程序）
     */
    @ApiModelProperty(value = "图片跳转路径（微信小程序）")
    @Column(name = "path")
    private String path;

    /**
     * 图片类型（0：轮播图 1：图文信息）
     */
    @ApiModelProperty(value = "图片类型（0：轮播图 1：图文信息）")
    @Column(name = "jhi_type")
    private Integer type;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    @Column(name = "jhi_sort")
    private Integer sort;

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
    @JsonIgnoreProperties("")
    private SysProduct product;

    @ManyToOne
    @JsonIgnoreProperties("")
    private SysClassify classify;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public SysBanner image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPath() {
        return path;
    }

    public SysBanner path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public SysBanner type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public SysBanner sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysBanner createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysBanner updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysProduct getProduct() {
        return product;
    }

    public SysBanner product(SysProduct sysProduct) {
        this.product = sysProduct;
        return this;
    }

    public void setProduct(SysProduct sysProduct) {
        this.product = sysProduct;
    }

    public SysClassify getClassify() {
        return classify;
    }

    public SysBanner classify(SysClassify sysClassify) {
        this.classify = sysClassify;
        return this;
    }

    public void setClassify(SysClassify sysClassify) {
        this.classify = sysClassify;
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
        SysBanner sysBanner = (SysBanner) o;
        if (sysBanner.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysBanner.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysBanner{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", path='" + getPath() + "'" +
            ", type=" + getType() +
            ", sort=" + getSort() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
