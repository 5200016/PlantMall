package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 模块表
 */
@ApiModel(description = "模块表")
@Entity
@Table(name = "sys_module")
public class SysModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    @Column(name = "name")
    private String name;

    /**
     * 模块图标
     */
    @ApiModelProperty(value = "模块图标")
    @Column(name = "icon")
    private String icon;

    /**
     * 模块大图
     */
    @ApiModelProperty(value = "模块大图")
    @Column(name = "image")
    private String image;

    /**
     * 大图显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "大图显示（0：不显示 1：显示）")
    @Column(name = "image_disable")
    private Integer imageDisable;

    /**
     * 类型（0：商品分类 1：预约服务，2：植物志）
     */
    @ApiModelProperty(value = "类型（0：商品分类 1：预约服务，2：植物志）")
    @Column(name = "jhi_type")
    private Integer type;

    /**
     * 风格类型（0：纵向排列 1：横向排列）
     */
    @ApiModelProperty(value = "风格类型（0：纵向排列 1：横向排列）")
    @Column(name = "style_type")
    private Integer styleType;

    /**
     * 首页菜单显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "首页菜单显示（0：不显示 1：显示）")
    @Column(name = "home_menu")
    private Integer homeMenu;

    /**
     * 首页底部显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "首页底部显示（0：不显示 1：显示）")
    @Column(name = "home_bottom")
    private Integer homeBottom;

    /**
     * 小程序跳转路径
     */
    @ApiModelProperty(value = "小程序跳转路径")
    @Column(name = "path")
    private String path;

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
    private SysClassify classify;

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

    public SysModule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public SysModule icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public SysModule image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImageDisable() {
        return imageDisable;
    }

    public SysModule imageDisable(Integer imageDisable) {
        this.imageDisable = imageDisable;
        return this;
    }

    public void setImageDisable(Integer imageDisable) {
        this.imageDisable = imageDisable;
    }

    public Integer getType() {
        return type;
    }

    public SysModule type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStyleType() {
        return styleType;
    }

    public SysModule styleType(Integer styleType) {
        this.styleType = styleType;
        return this;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }

    public Integer getHomeMenu() {
        return homeMenu;
    }

    public SysModule homeMenu(Integer homeMenu) {
        this.homeMenu = homeMenu;
        return this;
    }

    public void setHomeMenu(Integer homeMenu) {
        this.homeMenu = homeMenu;
    }

    public Integer getHomeBottom() {
        return homeBottom;
    }

    public SysModule homeBottom(Integer homeBottom) {
        this.homeBottom = homeBottom;
        return this;
    }

    public void setHomeBottom(Integer homeBottom) {
        this.homeBottom = homeBottom;
    }

    public String getPath() {
        return path;
    }

    public SysModule path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSort() {
        return sort;
    }

    public SysModule sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysModule createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysModule updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public SysClassify getClassify() {
        return classify;
    }

    public SysModule classify(SysClassify sysClassify) {
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
        SysModule sysModule = (SysModule) o;
        if (sysModule.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysModule.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysModule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", icon='" + getIcon() + "'" +
            ", image='" + getImage() + "'" +
            ", imageDisable=" + getImageDisable() +
            ", type=" + getType() +
            ", styleType=" + getStyleType() +
            ", homeMenu=" + getHomeMenu() +
            ", homeBottom=" + getHomeBottom() +
            ", path='" + getPath() + "'" +
            ", sort=" + getSort() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
