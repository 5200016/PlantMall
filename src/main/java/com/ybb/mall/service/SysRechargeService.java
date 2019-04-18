package com.ybb.mall.service;

import com.ybb.mall.domain.SysRecharge;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.recharge.InsertRechargeVM;
import com.ybb.mall.web.rest.vm.recharge.UpdateRechargeVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysRecharge.
 */
public interface SysRechargeService {

    /**
     * Save a sysRecharge.
     *
     * @param sysRecharge the entity to save
     * @return the persisted entity
     */
    SysRecharge save(SysRecharge sysRecharge);

    /**
     * Get all the sysRecharges.
     *
     * @return the list of entities
     */
    List<SysRecharge> findAll();


    /**
     * Get the "id" sysRecharge.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysRecharge> findOne(Long id);

    /**
     * Delete the "id" sysRecharge.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 查询充值项目列表（根据金额正序）
     */
    List<SysRecharge> findRechargeList();

    /**
     * 新增充值项目
     */
    ResultObj insertRecharge(InsertRechargeVM recharge);

    /**
     * 修改充值项目
     */
    ResultObj updateRecharge(UpdateRechargeVM recharge);

    /**
     * 删除充值项目
     */
    ResultObj deleteRecharge(Long id);
}
