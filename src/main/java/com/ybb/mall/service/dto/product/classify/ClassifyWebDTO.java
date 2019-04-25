package com.ybb.mall.service.dto.product.classify;

/**
 * @Description : 适配前端选择器数据类型
 * @Author 黄志成
 * @Date 2019-04-23
 * @Version
 */

public class ClassifyWebDTO {
    private Long id;
    private String name;

    public ClassifyWebDTO() {
    }

    public ClassifyWebDTO(Long id, String name) {
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
        return "ClassifyWebDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
