package com.ybb.mall.web.rest.vm.home.module;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 新增模块
 * @Author 黄志成
 * @Date 2019-05-06
 * @Version
 */

@ApiModel(description = "新增模块")
public class InsertModuleVM {
    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称")
    private String name;

    /**
     * 模块图标
     */
    @ApiModelProperty(value = "模块图标")
    private String icon;

    /**
     * 模块大图
     */
    @ApiModelProperty(value = "模块大图")
    private String image;

    /**
     * 大图显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "大图显示（0：不显示 1：显示）")
    private Integer imageDisable;

    /**
     * 类型（0：商品分类 1：预约服务，2：植物志）
     */
    @ApiModelProperty(value = "类型（0：商品分类 1：预约服务，2：植物志）")
    private Integer type;

    /**
     * 风格类型（0：纵向排列 1：横向排列）
     */
    @ApiModelProperty(value = "风格类型（0：纵向排列 1：横向排列）")
    private Integer styleType;

    /**
     * 首页菜单显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "首页菜单显示（0：不显示 1：显示）")
    private Integer homeMenu;

    /**
     * 首页底部显示（0：不显示 1：显示）
     */
    @ApiModelProperty(value = "首页底部显示（0：不显示 1：显示）")
    private Integer homeBottom;

    /**
     * 小程序跳转路径
     */
    @ApiModelProperty(value = "小程序跳转路径")
    private String path;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 类型数组
     */
    @ApiModelProperty(value = "类型数组")
    private List<Integer> typeList = new ArrayList<>();

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

    public List<Integer> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<Integer> typeList) {
        this.typeList = typeList;
    }

    @Override
    public String toString() {
        return "InsertModuleVM{" +
            "name='" + name + '\'' +
            ", icon='" + icon + '\'' +
            ", image='" + image + '\'' +
            ", imageDisable=" + imageDisable +
            ", type=" + type +
            ", styleType=" + styleType +
            ", homeMenu=" + homeMenu +
            ", homeBottom=" + homeBottom +
            ", path='" + path + '\'' +
            ", sort=" + sort +
            ", typeList=" + typeList +
            '}';
    }
}
