package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysRechargeService;
import com.ybb.mall.domain.SysRecharge;
import com.ybb.mall.repository.SysRechargeRepository;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.recharge.InsertRechargeVM;
import com.ybb.mall.web.rest.vm.recharge.UpdateRechargeVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysRecharge.
 */
@Service
@Transactional
public class SysRechargeServiceImpl implements SysRechargeService {

    private final Logger log = LoggerFactory.getLogger(SysRechargeServiceImpl.class);

    private final SysRechargeRepository sysRechargeRepository;

    public SysRechargeServiceImpl(SysRechargeRepository sysRechargeRepository) {
        this.sysRechargeRepository = sysRechargeRepository;
    }

    /**
     * Save a sysRecharge.
     *
     * @param sysRecharge the entity to save
     * @return the persisted entity
     */
    @Override
    public SysRecharge save(SysRecharge sysRecharge) {
        log.debug("Request to save SysRecharge : {}", sysRecharge);
        return sysRechargeRepository.save(sysRecharge);
    }

    /**
     * Get all the sysRecharges.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysRecharge> findAll() {
        log.debug("Request to get all SysRecharges");
        return sysRechargeRepository.findAll();
    }


    /**
     * Get one sysRecharge by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRecharge> findOne(Long id) {
        log.debug("Request to get SysRecharge : {}", id);
        return sysRechargeRepository.findById(id);
    }

    /**
     * Delete the sysRecharge by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRecharge : {}", id);
        sysRechargeRepository.deleteById(id);
    }

    @Override
    public List<SysRecharge> findRechargeList() {
        return sysRechargeRepository.findRechargeList();
    }

    @Override
    public ResultObj insertRecharge(InsertRechargeVM recharge) {
        SysRecharge sysRecharge = new SysRecharge();
        sysRecharge.setPrice(recharge.getPrice());
        sysRecharge.setCreateTime(DateUtil.getZoneDateTime());
        sysRecharge.setUpdateTime(DateUtil.getZoneDateTime());
        sysRechargeRepository.save(sysRecharge);
        return ResultObj.backInfo(true, 200, "新增成功", null);
    }

    @Override
    public ResultObj updateRecharge(UpdateRechargeVM recharge) {
        if(TypeUtils.isEmpty(recharge.getId())){
            ResultObj.backInfo(false, 200, "编辑失败（id不能为空）", null);
        }
        SysRecharge sysRecharge = new SysRecharge();
        sysRecharge.setId(recharge.getId());
        sysRecharge.setPrice(recharge.getPrice());
        sysRecharge.setCreateTime(recharge.getCreateTime());
        sysRecharge.setUpdateTime(DateUtil.getZoneDateTime());
        sysRechargeRepository.save(sysRecharge);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }

    @Override
    public ResultObj deleteRecharge(Long id) {
        sysRechargeRepository.deleteById(id);
        return ResultObj.backInfo(true, 200, "删除成功", null);
    }
}
