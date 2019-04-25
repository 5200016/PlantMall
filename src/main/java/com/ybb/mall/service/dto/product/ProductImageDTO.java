package com.ybb.mall.service.dto.product;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-04-23
 * @Version
 */

public class ProductImageDTO {
    private String url;

    public ProductImageDTO(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ProductImageDTO{" +
            "url='" + url + '\'' +
            '}';
    }
}
