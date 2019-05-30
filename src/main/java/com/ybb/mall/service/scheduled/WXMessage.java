package com.ybb.mall.service.scheduled;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.domain.SysForm;
import com.ybb.mall.repository.FormRepository;
import com.ybb.mall.service.dto.wx.message.WXMessageDTO;
import com.ybb.mall.service.dto.wx.message.WXTemplateDataDTO;
import com.ybb.mall.web.rest.util.OkHttpUtil;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.util.WXInterfaceUtil;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc :
 */

@Component
@EnableScheduling
public class WXMessage {

    private final FormRepository formRepository;

    public WXMessage(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void pushMessage() {
//        System.out.println("方法执行");
//        List<SysForm> formList = formRepository.findFormIdByUserId(28L);
//        if(!TypeUtils.isEmpty(formList)){
//            for (SysForm data : formList){
//                sendMessageToUser(data.getFormId());
//                formRepository.delete(data);
//            }
//        }

    }

    public void sendMessageToUser(String formId){
        String accessToken = WXInterfaceUtil.getAccessToken();

        String url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send" +
            "?access_token=" + accessToken;

        //拼接推送的模版
        WXMessageDTO wxMssVo = new WXMessageDTO();
        wxMssVo.setTouser("ojTgF5pS_wfJSYmAVMXtT8-d8UwU");//用户openid
        wxMssVo.setTemplate_id("bNTx9PsCi00RfLBDG3Fx3ke5sYJoc7dU2GKOikaZJEU");//模版id
        wxMssVo.setForm_id(formId);//formid
        Map<String, WXTemplateDataDTO> m = new HashMap<>(5);

        //keyword1：订单类型，keyword2：下单金额，keyword3：配送地址，keyword4：取件地址，keyword5备注
        WXTemplateDataDTO keyword1 = new WXTemplateDataDTO();
        keyword1.setValue("绿植养护");
        m.put("keyword1", keyword1);

        WXTemplateDataDTO keyword2 = new WXTemplateDataDTO();
        keyword2.setValue("黄先生");
        m.put("keyword2", keyword2);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword3 = new WXTemplateDataDTO();
        keyword3.setValue("18851190037");
        m.put("keyword3", keyword3);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword4 = new WXTemplateDataDTO();
        keyword4.setValue("弘阳时光里二期");
        m.put("keyword4", keyword4);
        wxMssVo.setData(m);

        WXTemplateDataDTO keyword5 = new WXTemplateDataDTO();
        keyword5.setValue("完成君子兰等16个植物的养护");
        m.put("keyword5", keyword5);
        wxMssVo.setData(m);

        MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=utf-8");
        String x1 = JSON.toJSONString(wxMssVo,true);
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE , x1);
        JSONObject object =  OkHttpUtil.postRequest(url , requestBody, null);
        System.out.println(object);
    }
}
