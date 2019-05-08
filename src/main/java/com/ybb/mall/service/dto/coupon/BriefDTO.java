package com.ybb.mall.service.dto.coupon;

/**
 * @Description : 商品或分类简略信息
 * @Author 黄志成
 * @Date 2019-05-08
 * @Version
 */

public class BriefDTO {
    private Long id;
    private String name;

    public BriefDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "BriefDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
