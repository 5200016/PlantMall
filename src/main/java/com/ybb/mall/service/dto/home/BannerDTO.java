package com.ybb.mall.service.dto.home;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 轮播
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public class BannerDTO {

    private Long id;

    private Integer type;

    private String image;

    private Integer sort;

    private ZonedDateTime createTime;

    private Long productId;

    private String productName;

    private Long classifyId;

    private String classifyName;

    private List<Long> cascadeId = new ArrayList<>();

    public BannerDTO(Long id, Integer type, String image, Integer sort, ZonedDateTime createTime, Long productId, String productName, Long classifyId, String classifyName) {
        this.id = id;
        this.type = type;
        this.image = image;
        this.sort = sort;
        this.createTime = createTime;
        this.productId = productId;
        this.productName = productName;
        this.classifyId = classifyId;
        this.classifyName = classifyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "BannerDTO{" +
            "id=" + id +
            ", type='" + type + '\'' +
            ", image='" + image + '\'' +
            ", sort=" + sort +
            ", createTime=" + createTime +
            ", productId=" + productId +
            ", productName='" + productName + '\'' +
            ", classifyId=" + classifyId +
            ", classifyName='" + classifyName + '\'' +
            ", cascadeId=" + cascadeId +
            '}';
    }
}
