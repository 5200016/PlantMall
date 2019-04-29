package com.ybb.mall.web.rest.vm.home.banner;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Description : 新增广告
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public class InsertBannerVM {
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String image;

    /**
     * 图片跳转路径（微信小程序）
     */
    @ApiModelProperty(value = "图片跳转路径（微信小程序）")
    private String path;

    /**
     * 图片类型（0：轮播图 1：图文信息）
     */
    @ApiModelProperty(value = "图片类型（0：轮播图 1：图文信息）")
    private Integer type;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 级联id集合
     */
    @ApiModelProperty(value = "级联id集合")
    private List<Long> cascadeId;

    public List<Long> getCascadeId() {
        return cascadeId;
    }

    public void setCascadeId(List<Long> cascadeId) {
        this.cascadeId = cascadeId;
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

    @Override
    public String toString() {
        return "InsertBannerVM{" +
            "image='" + image + '\'' +
            ", path='" + path + '\'' +
            ", type=" + type +
            ", sort=" + sort +
            ", cascadeId=" + cascadeId +
            '}';
    }
}
