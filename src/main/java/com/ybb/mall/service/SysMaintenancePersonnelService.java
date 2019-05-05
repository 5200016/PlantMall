package com.ybb.mall.service;

import com.ybb.mall.domain.SysMaintenancePersonnel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysMaintenancePersonnel.
 */
public interface SysMaintenancePersonnelService {

    /**
     * Save a sysMaintenancePersonnel.
     *
     * @param sysMaintenancePersonnel the entity to save
     * @return the persisted entity
     */
    SysMaintenancePersonnel save(SysMaintenancePersonnel sysMaintenancePersonnel);

    /**
     * Get all the sysMaintenancePersonnels.
     *
     * @return the list of entities
     */
    List<SysMaintenancePersonnel> findAll();


    /**
     * Get the "id" sysMaintenancePersonnel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysMaintenancePersonnel> findOne(Long id);

    /**
     * Delete the "id" sysMaintenancePersonnel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
