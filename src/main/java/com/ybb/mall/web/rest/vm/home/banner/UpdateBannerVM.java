package com.ybb.mall.web.rest.vm.home.banner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @Description : 修改广告
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@ApiModel(description = "修改广告")
public class UpdateBannerVM {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private ZonedDateTime createTime;

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

    @Override
    public String toString() {
        return "UpdateBannerVM{" +
            "id=" + id +
            ", image='" + image + '\'' +
            ", path='" + path + '\'' +
            ", type=" + type +
            ", sort=" + sort +
            ", createTime=" + createTime +
            ", cascadeId=" + cascadeId +
            '}';
    }
}
