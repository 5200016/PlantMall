package com.ybb.mall.service;

import com.ybb.mall.domain.SysMaintenanceFinish;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysMaintenanceFinish.
 */
public interface SysMaintenanceFinishService {

    /**
     * Save a sysMaintenanceFinish.
     *
     * @param sysMaintenanceFinish the entity to save
     * @return the persisted entity
     */
    SysMaintenanceFinish save(SysMaintenanceFinish sysMaintenanceFinish);

    /**
     * Get all the sysMaintenanceFinishes.
     *
     * @return the list of entities
     */
    List<SysMaintenanceFinish> findAll();


    /**
     * Get the "id" sysMaintenanceFinish.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysMaintenanceFinish> findOne(Long id);

    /**
     * Delete the "id" sysMaintenanceFinish.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
