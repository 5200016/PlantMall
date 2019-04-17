package com.ybb.mall.service;

import com.ybb.mall.domain.SysMemberLevel;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.member.MemberLevelVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysMemberLevel.
 */
public interface SysMemberLevelService {

    /**
     * Save a sysMemberLevel.
     *
     * @param sysMemberLevel the entity to save
     * @return the persisted entity
     */
    SysMemberLevel save(SysMemberLevel sysMemberLevel);

    /**
     * Get all the sysMemberLevels.
     *
     * @return the list of entities
     */
    List<SysMemberLevel> findAll();


    /**
     * Get the "id" sysMemberLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysMemberLevel> findOne(Long id);

    /**
     * Delete the "id" sysMemberLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 查询会员等级列表
     */
    List<SysMemberLevel> findMemberLevelList();

    /**
     * 修改会员等级列表
     */
    ResultObj updateMemberLevel(MemberLevelVM memberLevel);
}
