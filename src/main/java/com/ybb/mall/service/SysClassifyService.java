package com.ybb.mall.service;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.dto.classify.ClassifyDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.classify.UpdateClassifyVM;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysClassify.
 */
public interface SysClassifyService {

    /**
     * Save a sysClassify.
     *
     * @param sysClassify the entity to save
     * @return the persisted entity
     */
    SysClassify save(SysClassify sysClassify);

    /**
     * Get all the sysClassifies.
     *
     * @return the list of entities
     */
    List<SysClassify> findAll();


    /**
     * Get the "id" sysClassify.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysClassify> findOne(Long id);

    /**
     * Delete the "id" sysClassify.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 分页模糊查询商品分类列表（根据排序字段正序）
     * 条件：名称，类型
     */
    Page<ClassifyDTO> findClassifyList(String name, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 新增植物志
     */
    ResultObj insertClassify(InsertClassifyVM classifyVM);

    /**
     * 修改植物志
     */
    ResultObj updateClassify(UpdateClassifyVM classifyVM);

    /**
     * 删除植物志
     */
    ResultObj deleteClassify(Long id);
}
