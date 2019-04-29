package com.ybb.mall.service;

import com.ybb.mall.service.dto.product.cascade.ClassifyCascadeDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyGroupDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.product.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.product.classify.UpdateClassifyVM;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description : 商品分类
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public interface ClassifyService {

    /**
     * 分页模糊查询商品分类列表（根据排序字段正序）
     * 条件：名称，类型
     */
    Page<ClassifyDTO> findClassifyList(String name, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 新增商品分类
     */
    ResultObj insertClassify(InsertClassifyVM classifyVM);

    /**
     * 修改商品分类
     */
    ResultObj updateClassify(UpdateClassifyVM classifyVM);

    /**
     * 删除商品分类
     */
    ResultObj deleteClassify(Long id);

    /**
     * 根据类型查询分类
     */
    ClassifyGroupDTO findSysClassifyGroup();

    /**
     * 查询商品分类及其关联商品
     */
    List<ClassifyCascadeDTO> findClassifyAndProduct();
}
