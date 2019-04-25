package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysClassifyService;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.repository.SysClassifyRepository;
import com.ybb.mall.service.dto.product.classify.ClassifyGroupDTO;
import com.ybb.mall.service.dto.product.classify.ClassifyDTO;
import com.ybb.mall.service.mapper.SysClassifyMapper;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.product.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.product.classify.UpdateClassifyVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysClassify.
 */
@Service
@Transactional
public class SysClassifyServiceImpl implements SysClassifyService {

    private final Logger log = LoggerFactory.getLogger(SysClassifyServiceImpl.class);

    private final SysClassifyRepository sysClassifyRepository;

    private final SysClassifyMapper classifyMapper;

    public SysClassifyServiceImpl(SysClassifyRepository sysClassifyRepository, SysClassifyMapper classifyMapper) {
        this.sysClassifyRepository = sysClassifyRepository;
        this.classifyMapper = classifyMapper;
    }

    /**
     * Save a sysClassify.
     *
     * @param sysClassify the entity to save
     * @return the persisted entity
     */
    @Override
    public SysClassify save(SysClassify sysClassify) {
        log.debug("Request to save SysClassify : {}", sysClassify);
        return sysClassifyRepository.save(sysClassify);
    }

    /**
     * Get all the sysClassifies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysClassify> findAll() {
        log.debug("Request to get all SysClassifies");
        return sysClassifyRepository.findAll();
    }


    /**
     * Get one sysClassify by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysClassify> findOne(Long id) {
        log.debug("Request to get SysClassify : {}", id);
        return sysClassifyRepository.findById(id);
    }

    /**
     * Delete the sysClassify by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysClassify : {}", id);
        sysClassifyRepository.deleteById(id);
    }

    @Override
    public Page<ClassifyDTO> findClassifyList(String name, Integer type, Integer pageNum, Integer pageSize) {
        Integer typeFlag = 0;
        if (!TypeUtils.isEmpty(type) && !type.equals(-1)) {
            typeFlag = 1;
        }
        return sysClassifyRepository.findSysClassifyList(name, type, typeFlag, PageRequest.of(pageNum, pageSize)).map(classifyMapper::toDto);
    }

    @Override
    public ResultObj insertClassify(InsertClassifyVM classifyVM) {
        SysClassify classify = new SysClassify();
        classify.setName(classifyVM.getName());
        classify.setType(classifyVM.getType());
        classify.setCreateTime(DateUtil.getZoneDateTime());
        classify.setSort(classifyVM.getSort());
        classify.setUpdateTime(DateUtil.getZoneDateTime());
        sysClassifyRepository.save(classify);
        return ResultObj.backInfo(true, 200, "新增成功", null);
    }

    @Override
    public ResultObj updateClassify(UpdateClassifyVM classifyVM) {
        if (TypeUtils.isEmpty(classifyVM.getId())) {
            return ResultObj.backInfo(false, 200, "编辑失败（id不能为空）", null);
        }
        SysClassify classify = new SysClassify();
        classify.setId(classifyVM.getId());
        classify.setName(classifyVM.getName());
        classify.setType(classifyVM.getType());
        classify.setCreateTime(classifyVM.getCreateTime());
        classify.setSort(classifyVM.getSort());
        classify.setUpdateTime(DateUtil.getZoneDateTime());
        sysClassifyRepository.save(classify);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }

    @Override
    public ResultObj deleteClassify(Long id) {
        if (TypeUtils.isEmpty(id)) {
            return ResultObj.backInfo(false, 200, "删除失败（id不能为空）", null);
        }
        sysClassifyRepository.deleteById(id);
        return ResultObj.backInfo(true, 200, "删除成功", null);
    }

    @Override
    public ClassifyGroupDTO findSysClassifyGroup() {
        return new ClassifyGroupDTO(
            sysClassifyRepository.findSysClassifyByType(0),
            sysClassifyRepository.findSysClassifyByType(1)
        );
    }
}
