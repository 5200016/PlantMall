package com.ybb.mall.web.rest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.config.ApplicationProperties;
import com.ybb.mall.service.dto.wx.message.WXMessageDTO;
import com.ybb.mall.service.dto.wx.message.WXTemplateDataDTO;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * User : 黄志成
 * Date : 2018/12/21
 * Desc : 微信接口调用工具类
 */

@Component
public class WXInterfaceUtil {

    private static String appId ;

    private static String appSecret ;

    private final ApplicationProperties applicationProperties;

    public WXInterfaceUtil(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        appId = applicationProperties.getAppID();
        appSecret = applicationProperties.getAppSecret();
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
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
        JSONObject result = OkHttpUtil.getRequest(url , null);
        if(!result.containsKey("errcode")){
            return result.getString("access_token");
        }else{
            System.out.println(ErrorCode.errorMsg(Integer.parseInt(result.getString("errcode"))));
            return null;
        }
    }

    public static void sendMessageToMaintenance(String formId, String openid, String name, String phone, String address){
        String accessToken = getAccessToken();

        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
            "?access_token=" + accessToken;

        //拼接推送的模版
        WXMessageDTO wxMssVo = new WXMessageDTO();
        wxMssVo.setTemplate_id("bNTx9PsCi00RfLBDG3Fx3ke5sYJoc7dU2GKOikaZJEU");//模版id
        wxMssVo.setTouser(openid);//用户openid
        wxMssVo.setForm_id(formId);//formid

        Map<String, WXTemplateDataDTO> m = new HashMap<>(5);
        WXTemplateDataDTO keyword1 = new WXTemplateDataDTO();
        keyword1.setValue("绿植养护");
        m.put("keyword1", keyword1);

        WXTemplateDataDTO keyword2 = new WXTemplateDataDTO();
        keyword2.setValue(name);
        m.put("keyword2", keyword2);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword3 = new WXTemplateDataDTO();
        keyword3.setValue(phone);
        m.put("keyword3", keyword3);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword4 = new WXTemplateDataDTO();
        keyword4.setValue(address);
        m.put("keyword4", keyword4);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword5 = new WXTemplateDataDTO();
        keyword5.setValue("您明日有养护任务，请前往小程序查看");
        m.put("keyword5", keyword5);
        wxMssVo.setData(m);

        MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=utf-8");
        String x1 = JSON.toJSONString(wxMssVo,true);
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE , x1);
        OkHttpUtil.postRequest(url , requestBody, null);
    }

    public static void sendMessageToUser(String openid, String formId, String content){

        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
            "?access_token=" + getAccessToken();

        //拼接推送的模版
        WXMessageDTO wxMssVo = new WXMessageDTO();
        wxMssVo.setTouser(openid);
        wxMssVo.setForm_id(formId);
        wxMssVo.setTemplate_id("4WJAjUsKyfUM8g_LufoqZu2wXrD7yoAp21dgcofjOD0");//模版id

        Map<String, WXTemplateDataDTO> data = new HashMap<>(5);

        WXTemplateDataDTO keyword1 = new WXTemplateDataDTO();
        keyword1.setValue("绿植养护");
        data.put("keyword1", keyword1);

        WXTemplateDataDTO keyword2 = new WXTemplateDataDTO();
        keyword2.setValue(content);
        data.put("keyword2", keyword2);

        wxMssVo.setData(data);

        MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=utf-8");
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE , JSON.toJSONString(wxMssVo,true));
        OkHttpUtil.postRequest(url , requestBody, null);
    }
}
