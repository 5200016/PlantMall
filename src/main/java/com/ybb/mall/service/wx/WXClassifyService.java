package com.ybb.mall.service.wx;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.dto.wx.ClassifyDTO;
import com.ybb.mall.web.rest.util.ResultObj;

import java.util.List;

/**
 * @Description : 微信小程序-商品分类管理
 * @Author 黄志成
 * @Date 2019-05-14
 * @Version
 */

public interface WXClassifyService {
    /**
     * 查询商品分类
     * @return
     */
    List<ClassifyDTO> findWXClassify();


}
