package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * 商品表
 */
@ApiModel(description = "商品表")
@Entity
@Table(name = "sys_product")
public class SysProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @Column(name = "name")
    private String name;

    /**
     * 商品租赁价格
     */
    @ApiModelProperty(value = "商品租赁价格")
    @Column(name = "lease_price", precision = 10, scale = 2)
    private BigDecimal leasePrice;

    /**
     * 商品出售价格
     */
    @ApiModelProperty(value = "商品出售价格")
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @Column(name = "image")
    private String image;

    /**
     * 商品库存
     */
    @ApiModelProperty(value = "商品库存")
    @Column(name = "inventory")
    private Integer inventory;

    /**
     * 商品销售量
     */
    @ApiModelProperty(value = "商品销售量")
    @Column(name = "sale")
    private Integer sale;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述")
    @Lob
    @Column(name = "description")
    private String description;

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

    @ManyToMany
    @JoinTable(name = "sys_product_classify",
               joinColumns = @JoinColumn(name = "sys_products_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "classifies_id", referencedColumnName = "id"))
    private Set<SysClassify> classifies = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<SysProductImage> images = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private Set<SysShoppingProduct> shoppingProducts = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private Set<SysReview> reviews = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private Set<SysCollection> collections = new HashSet<>();
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

    public SysProduct name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLeasePrice() {
        return leasePrice;
    }

    public SysProduct leasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
        return this;
    }

    public void setLeasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public SysProduct price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public SysProduct image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getInventory() {
        return inventory;
    }

    public SysProduct inventory(Integer inventory) {
        this.inventory = inventory;
        return this;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getSale() {
        return sale;
    }

    public SysProduct sale(Integer sale) {
        this.sale = sale;
        return this;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public String getDescription() {
        return description;
    }

    public SysProduct description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysProduct createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysProduct updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Set<SysClassify> getClassifies() {
        return classifies;
    }

    public SysProduct classifies(Set<SysClassify> sysClassifies) {
        this.classifies = sysClassifies;
        return this;
    }

    public SysProduct addClassify(SysClassify sysClassify) {
        this.classifies.add(sysClassify);
        sysClassify.getProducts().add(this);
        return this;
    }

    public SysProduct removeClassify(SysClassify sysClassify) {
        this.classifies.remove(sysClassify);
        sysClassify.getProducts().remove(this);
        return this;
    }

    public void setClassifies(Set<SysClassify> sysClassifies) {
        this.classifies = sysClassifies;
    }

    public Set<SysProductImage> getImages() {
        return images;
    }

    public SysProduct images(Set<SysProductImage> sysProductImages) {
        this.images = sysProductImages;
        return this;
    }

    public SysProduct addImage(SysProductImage sysProductImage) {
        this.images.add(sysProductImage);
        sysProductImage.setProduct(this);
        return this;
    }

    public SysProduct removeImage(SysProductImage sysProductImage) {
        this.images.remove(sysProductImage);
        sysProductImage.setProduct(null);
        return this;
    }

    public void setImages(Set<SysProductImage> sysProductImages) {
        this.images = sysProductImages;
    }

    public Set<SysShoppingProduct> getShoppingProducts() {
        return shoppingProducts;
    }

    public SysProduct shoppingProducts(Set<SysShoppingProduct> sysShoppingProducts) {
        this.shoppingProducts = sysShoppingProducts;
        return this;
    }

    public SysProduct addShoppingProduct(SysShoppingProduct sysShoppingProduct) {
        this.shoppingProducts.add(sysShoppingProduct);
        sysShoppingProduct.setProduct(this);
        return this;
    }

    public SysProduct removeShoppingProduct(SysShoppingProduct sysShoppingProduct) {
        this.shoppingProducts.remove(sysShoppingProduct);
        sysShoppingProduct.setProduct(null);
        return this;
    }

    public void setShoppingProducts(Set<SysShoppingProduct> sysShoppingProducts) {
        this.shoppingProducts = sysShoppingProducts;
    }

    public Set<SysReview> getReviews() {
        return reviews;
    }

    public SysProduct reviews(Set<SysReview> sysReviews) {
        this.reviews = sysReviews;
        return this;
    }

    public SysProduct addReview(SysReview sysReview) {
        this.reviews.add(sysReview);
        sysReview.setProduct(this);
        return this;
    }

    public SysProduct removeReview(SysReview sysReview) {
        this.reviews.remove(sysReview);
        sysReview.setProduct(null);
        return this;
    }

    public void setReviews(Set<SysReview> sysReviews) {
        this.reviews = sysReviews;
    }

    public Set<SysCollection> getCollections() {
        return collections;
    }

    public SysProduct collections(Set<SysCollection> sysCollections) {
        this.collections = sysCollections;
        return this;
    }

    public SysProduct addCollection(SysCollection sysCollection) {
        this.collections.add(sysCollection);
        sysCollection.setProduct(this);
        return this;
    }

    public SysProduct removeCollection(SysCollection sysCollection) {
        this.collections.remove(sysCollection);
        sysCollection.setProduct(null);
        return this;
    }

    public void setCollections(Set<SysCollection> sysCollections) {
        this.collections = sysCollections;
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
        SysProduct sysProduct = (SysProduct) o;
        if (sysProduct.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysProduct.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysProduct{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", leasePrice=" + getLeasePrice() +
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", inventory=" + getInventory() +
            ", sale=" + getSale() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
