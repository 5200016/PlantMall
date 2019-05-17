package com.ybb.mall.service.dto.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

/**
 * @Description : 商品简略信息
 * @Author 黄志成
 * @Date 2019-05-05
 * @Version
 */

public class ProductBriefDTO {

    private Long id;

    private String name;

    private Integer inventory;

    private Integer sale;

    private BigDecimal price;

    private BigDecimal leasePrice;

    private String image;

    private Integer classifyType;

    public ProductBriefDTO(Long id, String name, Integer inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public ProductBriefDTO(Long id, String name, Integer sale, BigDecimal price, BigDecimal leasePrice, String image, Integer classifyType) {
        this.id = id;
        this.name = name;
        this.sale = sale;
        this.price = price;
        this.leasePrice = leasePrice;
        this.image = image;
        this.classifyType = classifyType;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(BigDecimal leasePrice) {
        this.leasePrice = leasePrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(Integer classifyType) {
        this.classifyType = classifyType;
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

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductBriefDTO that = (ProductBriefDTO) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(name, that.name)
            .append(inventory, that.inventory)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(name)
            .append(inventory)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "ProductBriefDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", inventory=" + inventory +
            '}';
    }


}
