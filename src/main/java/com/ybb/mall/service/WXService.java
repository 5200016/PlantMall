package com.ybb.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description : 微信小程序管理
 * @Author 黄志成
 * @Date 2019-05-09
 * @Version
 */

public interface WXService {

    /**
     * 解析用户信息
     * @param encryptedData
     * @param iv
     * @param code
     * @return
     */
    ResultObj decodeUserInfo(String encryptedData, String iv, String code);

    /**
     * 解析用户手机号
     * @param openid
     * @param encryptedData
     * @param iv
     * @return
     */
    ResultObj decodeUserPhone(String openid, String encryptedData, String iv, String code);
}
