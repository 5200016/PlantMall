package com.ybb.mall.service;

import com.ybb.mall.domain.SysRole;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysRole.
 */
public interface SysRoleService {

    /**
     * Save a sysRole.
     *
     * @param sysRole the entity to save
     * @return the persisted entity
     */
    SysRole save(SysRole sysRole);

    /**
     * Get all the sysRoles.
     *
     * @return the list of entities
     */
    List<SysRole> findAll();


    /**
     * Get the "id" sysRole.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysRole> findOne(Long id);

    /**
     * Delete the "id" sysRole.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
