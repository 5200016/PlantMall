package com.ybb.mall.service;

import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.member.MemberSettingVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysMemberSetting.
 */
public interface SysMemberSettingService {

    /**
     * Save a sysMemberSetting.
     *
     * @param sysMemberSetting the entity to save
     * @return the persisted entity
     */
    SysMemberSetting save(SysMemberSetting sysMemberSetting);

    /**
     * Get all the sysMemberSettings.
     *
     * @return the list of entities
     */
    List<SysMemberSetting> findAll();


    /**
     * Get the "id" sysMemberSetting.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysMemberSetting> findOne(Long id);

    /**
     * Delete the "id" sysMemberSetting.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 查询会员体系参数
     */
    SysMemberSetting findSysMemberSetting();

    /**
     * 修改会员体系参数
     */
    ResultObj updateSysMemberSetting(MemberSettingVM memberSetting);
}
