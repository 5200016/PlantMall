package com.ybb.mall.web.rest.vm.product.plant;

import java.time.ZonedDateTime;

/**
 * @Description : 修改植物志
 * @Author 黄志成
 * @Date 2019-04-19
 * @Version
 */

public class UpdatePlantLogVM {

    private Long id;

    private String name;

    private String image;

    private String description;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UpdatePlantLogVM{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", image='" + image + '\'' +
            ", description='" + description + '\'' +
            ", createTime=" + createTime +
            '}';
    }
}
