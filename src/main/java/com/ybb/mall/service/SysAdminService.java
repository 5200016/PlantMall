package com.ybb.mall.service;

import com.ybb.mall.domain.SysAdmin;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.AdminVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysAdmin.
 */
public interface SysAdminService {

    /**
     * Save a sysAdmin.
     *
     * @param sysAdmin the entity to save
     * @return the persisted entity
     */
    SysAdmin save(SysAdmin sysAdmin);

    /**
     * Get all the sysAdmins.
     *
     * @return the list of entities
     */
    List<SysAdmin> findAll();


    /**
     * Get the "id" sysAdmin.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysAdmin> findOne(Long id);

    /**
     * Delete the "id" sysAdmin.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 管理员登陆验证
     */
    ResultObj adminLogin(AdminVM admin);
}
