package com.ybb.mall.web.rest.vm;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-05-29
 * @Version
 */

public class UEditorVM {
    private String state;
    private String url;
    private String title;
    private String original;
    private String type;
    private String size;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "UEditorVM{" +
            "state='" + state + '\'' +
            ", url='" + url + '\'' +
            ", title='" + title + '\'' +
            ", original='" + original + '\'' +
            ", type='" + type + '\'' +
            ", size='" + size + '\'' +
            '}';
    }
}
