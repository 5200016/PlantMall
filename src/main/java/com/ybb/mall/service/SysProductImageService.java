package com.ybb.mall.service;

import com.ybb.mall.domain.SysProductImage;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysProductImage.
 */
public interface SysProductImageService {

    /**
     * Save a sysProductImage.
     *
     * @param sysProductImage the entity to save
     * @return the persisted entity
     */
    SysProductImage save(SysProductImage sysProductImage);

    /**
     * Get all the sysProductImages.
     *
     * @return the list of entities
     */
    List<SysProductImage> findAll();


    /**
     * Get the "id" sysProductImage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysProductImage> findOne(Long id);

    /**
     * Delete the "id" sysProductImage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
