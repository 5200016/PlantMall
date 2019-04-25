package com.ybb.mall.web.rest.vm.product.plant;

/**
 * @Description : 新增植物志
 * @Author 黄志成
 * @Date 2019-04-19
 * @Version
 */

public class InsertPlantLogVM {

    private String name;

    private String image;

    private String description;

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

    @Override
    public String toString() {
        return "InsertPlantLogVM{" +
            "name='" + name + '\'' +
            ", image='" + image + '\'' +
            ", description='" + description + '\'' +
            '}';
    }
}
