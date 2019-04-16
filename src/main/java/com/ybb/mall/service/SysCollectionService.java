package com.ybb.mall.service;

import com.ybb.mall.domain.SysCollection;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCollection.
 */
public interface SysCollectionService {

    /**
     * Save a sysCollection.
     *
     * @param sysCollection the entity to save
     * @return the persisted entity
     */
    SysCollection save(SysCollection sysCollection);

    /**
     * Get all the sysCollections.
     *
     * @return the list of entities
     */
    List<SysCollection> findAll();


    /**
     * Get the "id" sysCollection.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCollection> findOne(Long id);

    /**
     * Delete the "id" sysCollection.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
