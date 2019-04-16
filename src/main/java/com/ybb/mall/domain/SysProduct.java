package com.ybb.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

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

    @Column(name = "name")
    private String name;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "image")
    private String image;

    @Column(name = "jhi_specification")
    private String specification;

    @Column(name = "inventory")
    private Integer inventory;

    @Column(name = "sale")
    private Integer sale;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    @ManyToOne
    @JsonIgnoreProperties("products")
    private SysClassify classify;

    @OneToMany(mappedBy = "product")
    private Set<SysOrder> orders = new HashSet<>();
    @OneToMany(mappedBy = "product")
    private Set<SysShoppingCar> shoppingCars = new HashSet<>();
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

    public String getSpecification() {
        return specification;
    }

    public SysProduct specification(String specification) {
        this.specification = specification;
        return this;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
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

    public SysClassify getClassify() {
        return classify;
    }

    public SysProduct classify(SysClassify sysClassify) {
        this.classify = sysClassify;
        return this;
    }

    public void setClassify(SysClassify sysClassify) {
        this.classify = sysClassify;
    }

    public Set<SysOrder> getOrders() {
        return orders;
    }

    public SysProduct orders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
        return this;
    }

    public SysProduct addOrder(SysOrder sysOrder) {
        this.orders.add(sysOrder);
        sysOrder.setProduct(this);
        return this;
    }

    public SysProduct removeOrder(SysOrder sysOrder) {
        this.orders.remove(sysOrder);
        sysOrder.setProduct(null);
        return this;
    }

    public void setOrders(Set<SysOrder> sysOrders) {
        this.orders = sysOrders;
    }

    public Set<SysShoppingCar> getShoppingCars() {
        return shoppingCars;
    }

    public SysProduct shoppingCars(Set<SysShoppingCar> sysShoppingCars) {
        this.shoppingCars = sysShoppingCars;
        return this;
    }

    public SysProduct addShoppingCar(SysShoppingCar sysShoppingCar) {
        this.shoppingCars.add(sysShoppingCar);
        sysShoppingCar.setProduct(this);
        return this;
    }

    public SysProduct removeShoppingCar(SysShoppingCar sysShoppingCar) {
        this.shoppingCars.remove(sysShoppingCar);
        sysShoppingCar.setProduct(null);
        return this;
    }

    public void setShoppingCars(Set<SysShoppingCar> sysShoppingCars) {
        this.shoppingCars = sysShoppingCars;
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
            ", price=" + getPrice() +
            ", image='" + getImage() + "'" +
            ", specification='" + getSpecification() + "'" +
            ", inventory=" + getInventory() +
            ", sale=" + getSale() +
            ", description='" + getDescription() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
