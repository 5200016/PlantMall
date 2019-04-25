package com.ybb.mall.service.dto.product.classify;

import java.util.List;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-23
 * @Version
 */

public class ClassifyGroupDTO {
    private List<ClassifyWebDTO> sellClassify;
    private List<ClassifyWebDTO> leaseClassify;

    public ClassifyGroupDTO() {
    }

    public ClassifyGroupDTO(List<ClassifyWebDTO> sellClassify, List<ClassifyWebDTO> leaseClassify) {
        this.sellClassify = sellClassify;
        this.leaseClassify = leaseClassify;
    }

    public List<ClassifyWebDTO> getSellClassify() {
        return sellClassify;
    }

    public void setSellClassify(List<ClassifyWebDTO> sellClassify) {
        this.sellClassify = sellClassify;
    }

    public List<ClassifyWebDTO> getLeaseClassify() {
        return leaseClassify;
    }

    public void setLeaseClassify(List<ClassifyWebDTO> leaseClassify) {
        this.leaseClassify = leaseClassify;
    }

    @Override
    public String toString() {
        return "ClassifyGroupDTO{" +
            "sellClassify=" + sellClassify +
            ", leaseClassify=" + leaseClassify +
            '}';
    }
}
