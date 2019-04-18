package com.ybb.mall.web.rest.vm.recharge;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @Description : 新增充值项
 * @Author 黄志成
 * @Date 2019-04-17
 * @Version
 */

public class InsertRechargeVM {

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "InsertRechargeVM{" +
            "price=" + price +
            '}';
    }
}
