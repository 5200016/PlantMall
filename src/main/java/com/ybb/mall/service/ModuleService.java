package com.ybb.mall.service;

import com.ybb.mall.service.dto.home.ModuleDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.home.module.InsertModuleVM;
import com.ybb.mall.web.rest.vm.home.module.UpdateModuleVM;

import java.util.List;

/**
 * 首页模块
 */
public interface ModuleService {

    /**
     * 查询首页模块列表
     * @return
     */
    List<ModuleDTO> findHomeModuleList();

    /**
     * 新增模块
     * @param moduleVM
     * @return
     */
    ResultObj insertModule(InsertModuleVM moduleVM);

    /**
     * 修改模块
     * @param moduleVM
     * @return
     */
    ResultObj updateModule(UpdateModuleVM moduleVM);

    /**
     * 删除模块
     * @param id
     * @return
     */
    ResultObj deleteModule(Long id);
}
