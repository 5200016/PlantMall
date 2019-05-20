package com.ybb.mall.service.wx;

import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description : 微信小程序-首页模块管理
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

public interface WXModuleService {

    /**
     * 根据类型查询模块
     * @return
     */
    ResultObj findModuleListByType(Integer type);
}
