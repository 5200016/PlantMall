package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.domain.SysModule;
import com.ybb.mall.repository.ClassifyRepository;
import com.ybb.mall.repository.ModuleRepository;
import com.ybb.mall.service.ModuleService;
import com.ybb.mall.service.dto.home.ModuleDTO;
import com.ybb.mall.service.mapper.SysModuleMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.home.module.InsertModuleVM;
import com.ybb.mall.web.rest.vm.home.module.UpdateModuleVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 首页模块
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;

    private final ClassifyRepository classifyRepository;

    private final SysModuleMapper moduleMapper;

    public ModuleServiceImpl(ModuleRepository moduleRepository, ClassifyRepository classifyRepository, SysModuleMapper moduleMapper) {
        this.moduleRepository = moduleRepository;
        this.classifyRepository = classifyRepository;
        this.moduleMapper = moduleMapper;
    }

    @Override
    public List<ModuleDTO> findHomeModuleList() {
        List<ModuleDTO> moduleDTOList = moduleRepository.findHomeModuleList().stream().map(moduleMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
        for( ModuleDTO data : moduleDTOList){
            List<Integer> typeList = new ArrayList<>();
            if(!TypeUtils.isEmpty(data.getType()) && data.getType().equals(0)){
                typeList.add(data.getType());
                if(!TypeUtils.isEmpty(data.getClassify())){
                    typeList.add(data.getClassify().getId().intValue());
                    data.setTypeList(typeList);
                }else {
                    data.setTypeList(typeList);
                }
            }else {
                typeList.add(data.getType());
                data.setTypeList(typeList);
            }
        }
        return moduleDTOList;
    }

    @Override
    public ResultObj insertModule(InsertModuleVM moduleVM) {
        SysModule module = new SysModule();
        if(!TypeUtils.isEmpty(moduleVM.getTypeList())){
            switch(moduleVM.getTypeList().get(0)){
                case 0:
                    SysClassify classify = classifyRepository.findSysClassifyById(moduleVM.getTypeList().get(1).longValue());
                    module.setPath("pages/product/list/index?id=" + classify.getId() + "&classifyType=" + classify.getType() + "&classifyId=" + classify.getId());
                    module.setType(moduleVM.getTypeList().get(0));
                    module.setClassify(classify);
                    break;
                case 1:
                    module.setPath("pages/cart/appointment/index");
                    module.setType(moduleVM.getTypeList().get(0));
                    break;
                case 2:
                    module.setPath("pages/cart/plant_log/index");
                    module.setType(moduleVM.getTypeList().get(0));
                    break;
            }
        }
        module.setName(moduleVM.getName());
        module.setIcon(moduleVM.getIcon());
        module.setImage(moduleVM.getImage());
        module.setImageDisable(moduleVM.getImageDisable());
        module.setStyleType(moduleVM.getStyleType());
        module.setHomeBottom(moduleVM.getHomeBottom());
        module.setHomeMenu(moduleVM.getHomeMenu());
        module.setSort(moduleVM.getSort());
        module.setCreateTime(DateUtil.getZoneDateTime());
        module.setUpdateTime(DateUtil.getZoneDateTime());
        moduleRepository.save(module);
        return ResultObj.backCRUDSuccess("新增成功");
    }

    @Override
    public ResultObj updateModule(UpdateModuleVM moduleVM) {
        if(TypeUtils.isEmpty(moduleVM.getId())){
            return ResultObj.backCRUDError("编辑失败（id不能为空）");
        }
        SysModule module = new SysModule();
        module.setId(moduleVM.getId());
        if(!TypeUtils.isEmpty(moduleVM.getTypeList())){
            switch(moduleVM.getTypeList().get(0)){
                case 0:
                    SysClassify classify = classifyRepository.findSysClassifyById(moduleVM.getTypeList().get(1).longValue());
                    module.setType(moduleVM.getTypeList().get(0));
                    module.setPath("pages/product/list/index?id=" + classify.getId() + "&classifyType=" + classify.getType() + "&classifyId=" + classify.getId());
                    module.setClassify(classify);
                    break;
                case 1:
                    module.setPath("pages/cart/appointment/index");
                    module.setType(moduleVM.getTypeList().get(0));
                    break;
                case 2:
                    module.setPath("pages/cart/plant_log/index");
                    module.setType(moduleVM.getTypeList().get(0));
                    break;
            }
        }
        module.setName(moduleVM.getName());
        module.setIcon(moduleVM.getIcon());
        module.setImage(moduleVM.getImage());
        module.setImageDisable(moduleVM.getImageDisable());
        module.setHomeBottom(moduleVM.getHomeBottom());
        module.setStyleType(moduleVM.getStyleType());
        module.setHomeMenu(moduleVM.getHomeMenu());
        module.setSort(moduleVM.getSort());
        module.setCreateTime(moduleVM.getCreateTime());
        module.setUpdateTime(DateUtil.getZoneDateTime());
        moduleRepository.save(module);
        return ResultObj.backCRUDSuccess("编辑成功");
    }

    @Override
    public ResultObj deleteModule(Long id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("删除失败（id不能为空）");
        }
        moduleRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }
}
