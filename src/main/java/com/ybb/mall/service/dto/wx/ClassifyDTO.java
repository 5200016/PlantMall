package com.ybb.mall.service.dto.wx;

/**
 * @Description : 微信小程序-分类列表
 * @Author 黄志成
 * @Date 2019-05-14
 * @Version
 */

public class ClassifyDTO {
    private Long id;
    private String name;
    private Integer type;

    public ClassifyDTO(Long id, String name, Integer type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ClassifyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type=" + type +
            '}';
    }
}
