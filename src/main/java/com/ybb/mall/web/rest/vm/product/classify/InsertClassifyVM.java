package com.ybb.mall.web.rest.vm.product.classify;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description : 新增分类
 * @Author 黄志成
 * @Date 2019-04-22
 * @Version
 */

@ApiModel(description = "新增分类")
public class InsertClassifyVM {
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 分类类型（0：出售 1：租赁）
     */
    @ApiModelProperty(value = "分类类型（0：出售 1：租赁）")
    private Integer type;

    /**
     * 排序等级
     */
    @ApiModelProperty(value = "排序等级")
    private Integer sort;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "InsertClassifyVM{" +
            "name='" + name + '\'' +
            ", type=" + type +
            ", sort=" + sort +
            '}';
    }
}
