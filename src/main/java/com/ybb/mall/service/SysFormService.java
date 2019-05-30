package com.ybb.mall.service;

import com.ybb.mall.domain.SysForm;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysForm.
 */
public interface SysFormService {

    /**
     * Save a sysForm.
     *
     * @param sysForm the entity to save
     * @return the persisted entity
     */
    SysForm save(SysForm sysForm);

    /**
     * Get all the sysForms.
     *
     * @return the list of entities
     */
    List<SysForm> findAll();


    /**
     * Get the "id" sysForm.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysForm> findOne(Long id);

    /**
     * Delete the "id" sysForm.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
