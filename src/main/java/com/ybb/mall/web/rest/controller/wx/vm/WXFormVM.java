package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description : 微信formId实体
 * @Author 黄志成
 * @Date 2019-05-30
 * @Version
 */

public class WXFormVM {
    private Long userId;
    private String formId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return "WXFormVM{" +
            "userId=" + userId +
            ", formId='" + formId + '\'' +
            '}';
    }
}
