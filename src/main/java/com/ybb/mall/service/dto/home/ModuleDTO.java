package com.ybb.mall.service.dto.home;

import com.ybb.mall.domain.SysClassify;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 首页模块
 */
public class ModuleDTO implements Serializable {

    private Long id;

    private String name;

    private String icon;

    private String image;

    private Integer imageDisable;

    private Integer type;

    private List<Integer> typeList;

    private Integer styleType;

    private Integer homeMenu;

    private Integer homeBottom;

    private String path;

    private Integer sort;

    private SysClassify classify;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }

    public SysClassify getClassify() {
        return classify;
    }

    public void setClassify(SysClassify classify) {
        this.classify = classify;
    }

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImageDisable() {
        return imageDisable;
    }

    public void setImageDisable(Integer imageDisable) {
        this.imageDisable = imageDisable;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStyleType() {
        return styleType;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }

    public Integer getHomeMenu() {
        return homeMenu;
    }

    public void setHomeMenu(Integer homeMenu) {
        this.homeMenu = homeMenu;
    }

    public Integer getHomeBottom() {
        return homeBottom;
    }

    public void setHomeBottom(Integer homeBottom) {
        this.homeBottom = homeBottom;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    @Override
    public String toString() {
        return "ModuleDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", icon='" + icon + '\'' +
            ", image='" + image + '\'' +
            ", imageDisable=" + imageDisable +
            ", type=" + type +
            ", typeList=" + typeList +
            ", styleType=" + styleType +
            ", homeMenu=" + homeMenu +
            ", homeBottom='" + homeBottom + '\'' +
            ", path='" + path + '\'' +
            ", sort=" + sort +
            ", classify=" + classify +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }
}
