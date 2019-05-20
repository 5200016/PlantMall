package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysModule;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.repository.ModuleRepository;
import com.ybb.mall.service.dto.wx.ModuleDTO;
import com.ybb.mall.service.wx.WXModuleService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description : 微信小程序-首页模块管理
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

@Service
@Transactional
public class WXModuleServiceImpl implements WXModuleService {

    private final ModuleRepository moduleRepository;

    private final ClassifyRepository classifyRepository;

    public WXModuleServiceImpl(ModuleRepository moduleRepository, ClassifyRepository classifyRepository) {
        this.moduleRepository = moduleRepository;
        this.classifyRepository = classifyRepository;
    }

    @Override
    public ResultObj findModuleListByType(Integer type) {
        List<SysModule> moduleList = new ArrayList<>();
        List<ModuleDTO> bottomModuleMenu = new ArrayList<>();
        switch (type){
            case 0:
                moduleList = moduleRepository.findHomeModuleList();
                return ResultObj.back(200, moduleList);
            case 1:
                bottomModuleMenu = moduleRepository.findMenuList();
                for(ModuleDTO data : bottomModuleMenu){
                    if(!TypeUtils.isEmpty(data.getClassify())){
                        Long id = data.getClassify().getId();
                        SysClassify classify = classifyRepository.findSysClassifyById(id);
                        if(!TypeUtils.isEmpty(classify.getProducts())){
                            Set<SysProduct> products = classify.getProducts();
                            List<SysProduct> productList = products.stream().sorted(Comparator.comparing(SysProduct::getSale).reversed()).collect(Collectors.toList());
                            List<SysProduct> sysProductList = new ArrayList<>();
                            if(productList.size() >= 4){
                                for(int i = 0 ; i < 4 ; i ++){
                                    sysProductList.add(productList.get(i));
                                }
                            }else {
                                for(int i = 0 ; i < productList.size() ; i ++){
                                    sysProductList.add(productList.get(i));
                                }
                            }
                            data.setClassifyId(classify.getId());
                            data.setClassifyType(classify.getType());
                            data.setProducts(sysProductList);
                        }
                    }
                }
                return ResultObj.back(200, bottomModuleMenu);
        }
        return ResultObj.backCRUDError("暂无数据");
    }
}
