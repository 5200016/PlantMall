package com.ybb.mall.service.wx.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.service.dto.product.ProductBriefDTO;
import com.ybb.mall.service.dto.wx.ClassifyDTO;
import com.ybb.mall.service.wx.WXClassifyService;
import com.ybb.mall.web.rest.util.ResultObj;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description : 微信小程序-商品分类管理
 * @Author 黄志成
 * @Date 2019-05-14
 * @Version
 */

@Service
@Transactional
public class WXClassifyServiceImpl implements WXClassifyService {
    private final ClassifyRepository classifyRepository;

    public WXClassifyServiceImpl(ClassifyRepository classifyRepository) {
        this.classifyRepository = classifyRepository;
    }

    @Override
    public List<ClassifyDTO> findWXClassify() {
        return classifyRepository.findWXClassify();
    }


}
