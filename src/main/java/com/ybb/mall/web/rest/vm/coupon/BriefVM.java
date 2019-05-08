package com.ybb.mall.web.rest.vm.coupon;

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
    public String toString() {
        return "BriefVM{" +
            "id=" + id +
            '}';
    }
}
