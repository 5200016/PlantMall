package com.ybb.mall.web.rest.vm.coupon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @Description : 商品或分类简略信息实体
 * @Author 黄志成
 * @Date 2019-05-08
 * @Version
 */

public class BriefVM {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BriefVM briefVM = (BriefVM) o;

        return new EqualsBuilder()
            .append(id, briefVM.id)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "BriefVM{" +
            "id=" + id +
            '}';
    }
}
