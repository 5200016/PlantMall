package com.ybb.mall.service.dto.wx;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProduct;

import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

public class ModuleDTO {

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块图标
     */
    private String icon;

    /**
     * 模块大图
     */
    private String image;

    /**
     * 大图显示（0：不显示 1：显示）
     */
    private Integer imageDisable;

    /**
     * 类型（0：商品分类 1：预约服务，2：植物志）
     */
    private Integer type;

    /**
     * 风格类型（0：纵向排列 1：横向排列）
     */
    private Integer styleType;

    /**
     * 首页菜单显示（0：不显示 1：显示）
     */
    private Integer homeMenu;

    /**
     * 首页底部显示（0：不显示 1：显示）
     */
    private Integer homeBottom;

    /**
     * 小程序跳转路径
     */
    private String path;

    /**
     * 商品分类
     */
    private SysClassify classify;

    /**
     * 商品分类id
     */
    private Long classifyId;

    /**
     * 商品分类类型
     */
    private Integer classifyType;

    /**
     * 商品列表
     */
    List<SysProduct> products;

    public ModuleDTO(String name, String icon, String image, Integer imageDisable, Integer type, Integer styleType, Integer homeMenu, Integer homeBottom, String path, SysClassify classify) {
        this.name = name;
        this.icon = icon;
        this.image = image;
        this.imageDisable = imageDisable;
        this.type = type;
        this.styleType = styleType;
        this.homeMenu = homeMenu;
        this.homeBottom = homeBottom;
        this.path = path;
        this.classify = classify;
    }

    public SysClassify getClassify() {
        return classify;
    }

    public void setClassify(SysClassify classify) {
        this.classify = classify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getImageDisable() {
        return imageDisable;
    }

    public void setImageDisable(Integer imageDisable) {
        this.imageDisable = imageDisable;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStyleType() {
        return styleType;
    }

    public void setStyleType(Integer styleType) {
        this.styleType = styleType;
    }

    public Integer getHomeMenu() {
        return homeMenu;
    }

    public void setHomeMenu(Integer homeMenu) {
        this.homeMenu = homeMenu;
    }

    public Integer getHomeBottom() {
        return homeBottom;
    }

    public void setHomeBottom(Integer homeBottom) {
        this.homeBottom = homeBottom;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Long classifyId) {
        this.classifyId = classifyId;
    }

    public Integer getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(Integer classifyType) {
        this.classifyType = classifyType;
    }

    public List<SysProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SysProduct> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ModuleDTO{" +
            "name='" + name + '\'' +
            ", icon='" + icon + '\'' +
            ", image='" + image + '\'' +
            ", imageDisable=" + imageDisable +
            ", type=" + type +
            ", styleType=" + styleType +
            ", homeMenu=" + homeMenu +
            ", homeBottom=" + homeBottom +
            ", path='" + path + '\'' +
            ", classify=" + classify +
            ", classifyId=" + classifyId +
            ", classifyType=" + classifyType +
            ", products=" + products +
            '}';
    }
}
