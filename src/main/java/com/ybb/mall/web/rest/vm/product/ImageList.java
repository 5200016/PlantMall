package com.ybb.mall.web.rest.vm.product;

/**
 * @Description : 图片列表
 * @Author 黄志成
 * @Date 2019-04-22
 * @Version
 */

public class ImageList {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ImageList{" +
            "url='" + url + '\'' +
            '}';
    }
}
