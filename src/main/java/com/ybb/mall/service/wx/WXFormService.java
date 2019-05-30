package com.ybb.mall.service.wx;

import com.ybb.mall.web.rest.controller.wx.vm.WXFormVM;
import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description : 微信小程序-微信formId管理
 * @Author 黄志成
 * @Date 2019-05-30
 * @Version
 */

public interface WXFormService {

    /**
     * 新增formId
     * @param wxFormVM
     * @return
     */
    ResultObj insertForm(WXFormVM wxFormVM);
}
