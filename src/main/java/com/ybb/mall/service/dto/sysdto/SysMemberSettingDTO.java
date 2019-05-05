package com.ybb.mall.service.dto.sysdto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the SysMemberSetting entity.
 */
public class SysMemberSettingDTO implements Serializable {

    private Long id;

    private Integer growthProportion;

    private Integer reviewGrowthValue;

    private Integer checkGrowthValue;

    private Integer integralProportion;

    private Integer reviewIntegralValue;

    private Integer checkIntegralValue;

    private Integer integralProportionCash;

    private ZonedDateTime createTime;

    private ZonedDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrowthProportion() {
        return growthProportion;
    }

    public void setGrowthProportion(Integer growthProportion) {
        this.growthProportion = growthProportion;
    }

    public Integer getReviewGrowthValue() {
        return reviewGrowthValue;
    }

    public void setReviewGrowthValue(Integer reviewGrowthValue) {
        this.reviewGrowthValue = reviewGrowthValue;
    }

    public Integer getCheckGrowthValue() {
        return checkGrowthValue;
    }

    public void setCheckGrowthValue(Integer checkGrowthValue) {
        this.checkGrowthValue = checkGrowthValue;
    }

    public Integer getIntegralProportion() {
        return integralProportion;
    }

    public void setIntegralProportion(Integer integralProportion) {
        this.integralProportion = integralProportion;
    }

    public Integer getReviewIntegralValue() {
        return reviewIntegralValue;
    }

    public void setReviewIntegralValue(Integer reviewIntegralValue) {
        this.reviewIntegralValue = reviewIntegralValue;
    }

    public Integer getCheckIntegralValue() {
        return checkIntegralValue;
    }

    public void setCheckIntegralValue(Integer checkIntegralValue) {
        this.checkIntegralValue = checkIntegralValue;
    }

    public Integer getIntegralProportionCash() {
        return integralProportionCash;
    }

    public void setIntegralProportionCash(Integer integralProportionCash) {
        this.integralProportionCash = integralProportionCash;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SysMemberSettingDTO sysMemberSettingDTO = (SysMemberSettingDTO) o;
        if (sysMemberSettingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysMemberSettingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysMemberSettingDTO{" +
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
