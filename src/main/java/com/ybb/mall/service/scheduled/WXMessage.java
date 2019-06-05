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

// @Component
// @EnableScheduling
public class WXMessage {

    private final FormRepository formRepository;

    public WXMessage(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    // @Scheduled(cron = "0/30 * * * * ?")
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
}
