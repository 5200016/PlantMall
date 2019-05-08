package com.ybb.mall.web.rest.vm.coupon;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 新增优惠券
 * @Author 黄志成
 * @Date 2019-05-07
 * @Version
 */

public class InsertCouponVM {
    /**
     * 优惠券类型（0：商品优惠券 1：商品通用券 2：租赁优惠券）
     */
    @ApiModelProperty(value = "优惠券类型（0：商品优惠券 1：商品通用券 2：租赁优惠券）")
    private Integer type;

    /**
     * 优惠券名称
     */
    @ApiModelProperty(value = "优惠券名称")
    private String name;

    /**
     * 优惠券面值
     */
    @ApiModelProperty(value = "优惠券面值")
    private BigDecimal value;

    /**
     * 发放数量
     */
    @ApiModelProperty(value = "发放数量")
    private Integer quantity;

    /**
     * 限领数量
     */
    @ApiModelProperty(value = "限领数量")
    private Integer limit;

    /**
     * 有效期（起始日期）
     */
    @ApiModelProperty(value = "有效期（起始日期）")
    private ZonedDateTime startTime;

    /**
     * 有效期（截止日期）
     */
    @ApiModelProperty(value = "有效期（截止日期）")
    private ZonedDateTime endTime;

    /**
     * 使用描述
     */
    @ApiModelProperty(value = "使用描述")
    private String description;

    /**
     * 满减
     */
    @ApiModelProperty(value = "满减")
    private BigDecimal moneyOff;

    /**
     * 适用范围（0：指定商品 1：分类商品）
     */
    @ApiModelProperty(value = "适用范围（0：指定商品 1：分类商品）")
    private Integer range;

    /**
     * 商品
     */
    @ApiModelProperty(value = "商品")
    private List<BriefVM> product = new ArrayList<>();

    /**
     * 商品分类
     */
    @ApiModelProperty(value = "商品分类")
    private List<BriefVM> classify = new ArrayList<>();

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoneyOff() {
        return moneyOff;
    }

    public void setMoneyOff(BigDecimal moneyOff) {
        this.moneyOff = moneyOff;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public List<BriefVM> getProduct() {
        return product;
    }

    public void setProduct(List<BriefVM> product) {
        this.product = product;
    }

    public List<BriefVM> getClassify() {
        return classify;
    }

    public void setClassify(List<BriefVM> classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "InsertCouponVM{" +
            "type=" + type +
            ", name='" + name + '\'' +
            ", value=" + value +
            ", quantity=" + quantity +
            ", limit=" + limit +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", description='" + description + '\'' +
            ", moneyOff=" + moneyOff +
            ", range=" + range +
            ", product=" + product +
            ", classify=" + classify +
            '}';
    }
}
