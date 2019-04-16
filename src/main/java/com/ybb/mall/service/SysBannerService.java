package com.ybb.mall.service;

import com.ybb.mall.domain.SysBanner;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysBanner.
 */
public interface SysBannerService {

    /**
     * Save a sysBanner.
     *
     * @param sysBanner the entity to save
     * @return the persisted entity
     */
    SysBanner save(SysBanner sysBanner);

    /**
     * Get all the sysBanners.
     *
     * @return the list of entities
     */
    List<SysBanner> findAll();


    /**
     * Get the "id" sysBanner.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysBanner> findOne(Long id);

    /**
     * Delete the "id" sysBanner.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
