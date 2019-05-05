package com.ybb.mall.service.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.service.dto.product.classify.ClassifyWebDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description : 商品列表
 * @Author 黄志成
 * @Date 2019-04-22
 * @Version
 */

public class ProductDTO implements Serializable {

    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品租赁价格
     */
    private BigDecimal leasePrice;

    /**
     * 商品出售价格
     */
    private BigDecimal price;

    /**
     * 商品库存
     */
    private Integer inventory;

    /**
     * 商品销售量
     */
    private Integer sale;

    /**
     * 商品描述
     */
    private String description;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    private List<ProductImageDTO> imageList = new ArrayList<>();

    private List<Long> classifyId = new ArrayList<>();

    @JsonIgnore
    private Set<SysClassify> classifies = new HashSet<>();

    @JsonIgnore
    private Set<SysProductImage> images = new HashSet<>();

    public List<Long> getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(List<Long> classifyId) {
        this.classifyId = classifyId;
    }

    public Set<SysProductImage> getImages() {
        return images;
    }

    public void setImages(Set<SysProductImage> images) {
        this.images = images;
    }

    public Set<SysClassify> getClassifies() {
        return classifies;
    }

    public void setClassifies(Set<SysClassify> classifies) {
        this.classifies = classifies;
    }

    public BigDecimal getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public List<ProductImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<ProductImageDTO> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", leasePrice=" + leasePrice +
            ", price=" + price +
            ", inventory=" + inventory +
            ", sale=" + sale +
            ", description='" + description + '\'' +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", imageList=" + imageList +
            ", classifyId=" + classifyId +
            '}';
    }
}
