package com.ybb.mall.web.rest.controller.wx.vm.order;

/**
 * @Description : 更新订单状态
 * @Author 黄志成
 * @Date 2019-06-04
 * @Version
 */

public class UpdateOrderStatusVM {
    private Long id;
    private String payNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    @Override
    public String toString() {
        return "UpdateOrderStatusVM{" +
            "id=" + id +
            ", payNo='" + payNo + '\'' +
            '}';
    }
}
