package com.ybb.mall.web.rest.controller.wx.vm.review;

/**
 * @Description : 评论信息
 * @Author 黄志成
 * @Date 2019-06-10
 * @Version
 */

public class DataVM {
    private Long productId;
    private String content;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "DataVM{" +
            "productId=" + productId +
            ", content='" + content + '\'' +
            '}';
    }
}
