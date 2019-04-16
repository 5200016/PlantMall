package com.ybb.mall.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 会员设置表
 */
@ApiModel(description = "会员设置表")
@Entity
@Table(name = "sys_member_setting")
public class SysMemberSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "growth_proportion")
    private Integer growthProportion;

    @Column(name = "review_growth_value")
    private Integer reviewGrowthValue;

    @Column(name = "check_growth_value")
    private Integer checkGrowthValue;

    @Column(name = "integral_proportion")
    private Integer integralProportion;

    @Column(name = "review_integral_value")
    private Integer reviewIntegralValue;

    @Column(name = "check_integral_value")
    private Integer checkIntegralValue;

    @Column(name = "integral_proportion_cash")
    private Integer integralProportionCash;

    @Column(name = "create_time")
    private ZonedDateTime createTime;

    @Column(name = "update_time")
    private ZonedDateTime updateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrowthProportion() {
        return growthProportion;
    }

    public SysMemberSetting growthProportion(Integer growthProportion) {
        this.growthProportion = growthProportion;
        return this;
    }

    public void setGrowthProportion(Integer growthProportion) {
        this.growthProportion = growthProportion;
    }

    public Integer getReviewGrowthValue() {
        return reviewGrowthValue;
    }

    public SysMemberSetting reviewGrowthValue(Integer reviewGrowthValue) {
        this.reviewGrowthValue = reviewGrowthValue;
        return this;
    }

    public void setReviewGrowthValue(Integer reviewGrowthValue) {
        this.reviewGrowthValue = reviewGrowthValue;
    }

    public Integer getCheckGrowthValue() {
        return checkGrowthValue;
    }

    public SysMemberSetting checkGrowthValue(Integer checkGrowthValue) {
        this.checkGrowthValue = checkGrowthValue;
        return this;
    }

    public void setCheckGrowthValue(Integer checkGrowthValue) {
        this.checkGrowthValue = checkGrowthValue;
    }

    public Integer getIntegralProportion() {
        return integralProportion;
    }

    public SysMemberSetting integralProportion(Integer integralProportion) {
        this.integralProportion = integralProportion;
        return this;
    }

    public void setIntegralProportion(Integer integralProportion) {
        this.integralProportion = integralProportion;
    }

    public Integer getReviewIntegralValue() {
        return reviewIntegralValue;
    }

    public SysMemberSetting reviewIntegralValue(Integer reviewIntegralValue) {
        this.reviewIntegralValue = reviewIntegralValue;
        return this;
    }

    public void setReviewIntegralValue(Integer reviewIntegralValue) {
        this.reviewIntegralValue = reviewIntegralValue;
    }

    public Integer getCheckIntegralValue() {
        return checkIntegralValue;
    }

    public SysMemberSetting checkIntegralValue(Integer checkIntegralValue) {
        this.checkIntegralValue = checkIntegralValue;
        return this;
    }

    public void setCheckIntegralValue(Integer checkIntegralValue) {
        this.checkIntegralValue = checkIntegralValue;
    }

    public Integer getIntegralProportionCash() {
        return integralProportionCash;
    }

    public SysMemberSetting integralProportionCash(Integer integralProportionCash) {
        this.integralProportionCash = integralProportionCash;
        return this;
    }

    public void setIntegralProportionCash(Integer integralProportionCash) {
        this.integralProportionCash = integralProportionCash;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public SysMemberSetting createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public SysMemberSetting updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
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
        SysMemberSetting sysMemberSetting = (SysMemberSetting) o;
        if (sysMemberSetting.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMemberSetting.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMemberSetting{" +
            "id=" + getId() +
            ", growthProportion=" + getGrowthProportion() +
            ", reviewGrowthValue=" + getReviewGrowthValue() +
            ", checkGrowthValue=" + getCheckGrowthValue() +
            ", integralProportion=" + getIntegralProportion() +
            ", reviewIntegralValue=" + getReviewIntegralValue() +
            ", checkIntegralValue=" + getCheckIntegralValue() +
            ", integralProportionCash=" + getIntegralProportionCash() +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            "}";
    }
}
