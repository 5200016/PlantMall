package com.ybb.mall.service;

import com.ybb.mall.domain.SysModule;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysModule.
 */
public interface SysModuleService {

    /**
     * Save a sysModule.
     *
     * @param sysModule the entity to save
     * @return the persisted entity
     */
    SysModule save(SysModule sysModule);

    /**
     * Get all the sysModules.
     *
     * @return the list of entities
     */
    List<SysModule> findAll();


    /**
     * Get the "id" sysModule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysModule> findOne(Long id);

    /**
     * Delete the "id" sysModule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
