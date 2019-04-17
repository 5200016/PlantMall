package com.ybb.mall.web.rest.vm.member;

import java.time.ZonedDateTime;

/**
 * @Description : 会员等级设置
 * @Author 黄志成
 * @Date 2019-04-16
 * @Version
 */

public class MemberLevelVM {
    private Long id;

    private String name;

    private Integer level;

    private Integer leftValue;

    private Integer rightValue;

    private Double discount;

    private ZonedDateTime createTime;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(Integer leftValue) {
        this.leftValue = leftValue;
    }

    public Integer getRightValue() {
        return rightValue;
    }

    public void setRightValue(Integer rightValue) {
        this.rightValue = rightValue;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MemberLevelVM{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", level=" + level +
            ", leftValue=" + leftValue +
            ", rightValue=" + rightValue +
            ", discount=" + discount +
            ", createTime=" + createTime +
            '}';
    }
}
