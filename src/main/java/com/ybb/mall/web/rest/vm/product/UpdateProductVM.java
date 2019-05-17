package com.ybb.mall.web.rest.vm.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @Description : 修改商品
 * @Author 黄志成
 * @Date 2019-04-22
 * @Version
 */

@ApiModel(description = "修改商品")
public class UpdateProductVM {

    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 缩略图
     */
    @ApiModelProperty(value = "缩略图")
    private String image;

    /**
     * 商品租赁价格
     */
    @ApiModelProperty(value = "商品租赁价格")
    private BigDecimal leasePrice;

    /**
     * 商品出售价格
     */
    @ApiModelProperty(value = "商品出售价格")
    private BigDecimal price;

    /**
     * 商品库存
     */
    @ApiModelProperty(value = "商品库存")
    private Integer inventory;

    /**
     * 商品销售量
     */
    @ApiModelProperty(value = "商品销售量")
    private Integer sale;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    private String description;

    /**
     * 商品图片集合
     */
    @ApiModelProperty(value = "商品图片集合")
    private List<ImageList> imageList;

    /**
     * 商品分类id集合
     */
    @ApiModelProperty(value = "商品分类id集合")
    private List<Long> classifyId;

    private ZonedDateTime createTime;

    public List<Long> getClassifyId() {
        return classifyId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setClassifyId(List<Long> classifyId) {
        this.classifyId = classifyId;
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

    public List<ImageList> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageList> imageList) {
        this.imageList = imageList;
    }

    @Override
    public String toString() {
        return "UpdateProductVM{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", image='" + image + '\'' +
            ", leasePrice=" + leasePrice +
            ", price=" + price +
            ", inventory=" + inventory +
            ", sale=" + sale +
            ", description='" + description + '\'' +
            ", imageList=" + imageList +
            ", classifyId=" + classifyId +
            ", createTime=" + createTime +
            '}';
    }
}
