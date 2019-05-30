package com.ybb.mall.web.rest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.config.ApplicationProperties;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

/**
 * User : 黄志成
 * Date : 2018/12/21
 * Desc : 微信接口调用工具类
 */

@Component
public class WXInterfaceUtil {

    private static String AppId ;

    private static String AppSecret ;

    private final ApplicationProperties applicationProperties;

    public WXInterfaceUtil(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        AppId = applicationProperties.getAppID();
        AppSecret = applicationProperties.getAppSecret();
    }

    public static void sendGroupMessage(String content, String openId) {

//        Text texts = new Text();
//        texts.setContent(content);
//
//        TextInfo textInfo = new TextInfo();
//        textInfo.setTouser(openId);
//        textInfo.setText(texts);
//        textInfo.setMsgtype("text");
//
//        MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=utf-8");
//        String x1 = JSON.toJSONString(textInfo,true);
//        System.out.println(x1);
//        String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=" + getAccessToken();
//        RequestBody requestBody = RequestBody.create(MEDIA_TYPE , x1);
//        JSONObject result = OkHttpUtil.postRequest(url , requestBody , null);
//        System.out.println("++++++++++++");
//        System.out.println(result);
//        System.out.println("++++++++++++");
    }

    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppId + "&secret=" + AppSecret;
        JSONObject result = OkHttpUtil.getRequest(url , null);
        if(!result.containsKey("errcode")){
            return result.getString("access_token");
        }else{
            System.out.println(ErrorCode.errorMsg(Integer.parseInt(result.getString("errcode"))));
            return null;
        }
    }
}
