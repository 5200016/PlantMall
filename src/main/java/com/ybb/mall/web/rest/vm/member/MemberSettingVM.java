package com.ybb.mall.web.rest.vm.member;

import java.time.ZonedDateTime;

/**
 * @Description : 会员体系参数
 * @Author 黄志成
 * @Date 2019-04-16
 * @Version
 */

public class MemberSettingVM {
    private Long id;

    private Integer growthProportion;

    private Integer reviewGrowthValue;

    private Integer checkGrowthValue;

    private Integer integralProportion;

    private Integer reviewIntegralValue;

    private Integer checkIntegralValue;

    private Integer integralProportionCash;

    private ZonedDateTime createTime;

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

    @Override
    public String toString() {
        return "MemberSettingVM{" +
            "id=" + id +
            ", growthProportion=" + growthProportion +
            ", reviewGrowthValue=" + reviewGrowthValue +
            ", checkGrowthValue=" + checkGrowthValue +
            ", integralProportion=" + integralProportion +
            ", reviewIntegralValue=" + reviewIntegralValue +
            ", checkIntegralValue=" + checkIntegralValue +
            ", integralProportionCash=" + integralProportionCash +
            ", createTime=" + createTime +
            '}';
    }
}
