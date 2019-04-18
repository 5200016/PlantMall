package com.ybb.mall.web.rest.vm.recharge;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @Description : 修改充值项
 * @Author 黄志成
 * @Date 2019-04-17
 * @Version
 */

public class UpdateRechargeVM {

    private Long id;

    private BigDecimal price;

    private ZonedDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UpdateRechargeVM{" +
            "id=" + id +
            ", price=" + price +
            ", createTime=" + createTime +
            '}';
    }
}
