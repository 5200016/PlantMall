package com.ybb.mall.service;

import com.ybb.mall.domain.SysClassify;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysClassify.
 */
public interface SysClassifyService {

    /**
     * Save a sysClassify.
     *
     * @param sysClassify the entity to save
     * @return the persisted entity
     */
    SysClassify save(SysClassify sysClassify);

    /**
     * Get all the sysClassifies.
     *
     * @return the list of entities
     */
    List<SysClassify> findAll();


    /**
     * Get the "id" sysClassify.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysClassify> findOne(Long id);

    /**
     * Delete the "id" sysClassify.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
