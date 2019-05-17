package com.ybb.mall.web.rest.controller.wx.vm;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-05-10
 * @Version
 */

public class WXBindVM {
    private String openid;
    private String encryptedData;
    private String iv;
    private String code;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "WXBindVM{" +
            "openid='" + openid + '\'' +
            ", encryptedData='" + encryptedData + '\'' +
            ", iv='" + iv + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}
