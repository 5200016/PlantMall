package com.ybb.mall.service.dto.coupon;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 商品和分类简略信息
 * @Author 黄志成
 * @Date 2019-05-08
 * @Version
 */

public class BriefListDTO {
    List<BriefDTO> product = new ArrayList<>();
    List<BriefDTO> classify = new ArrayList<>();

    public BriefListDTO(List<BriefDTO> product, List<BriefDTO> classify) {
        this.product = product;
        this.classify = classify;
    }

    public List<BriefDTO> getProduct() {
        return product;
    }

    public void setProduct(List<BriefDTO> product) {
        this.product = product;
    }

    public List<BriefDTO> getClassify() {
        return classify;
    }

    public void setClassify(List<BriefDTO> classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "BriefListDTO{" +
            "product=" + product +
            ", classify=" + classify +
            '}';
    }
}
