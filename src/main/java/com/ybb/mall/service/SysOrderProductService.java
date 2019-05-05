package com.ybb.mall.service;

import com.ybb.mall.domain.SysOrderProduct;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysOrderProduct.
 */
public interface SysOrderProductService {

    /**
     * Save a sysOrderProduct.
     *
     * @param sysOrderProduct the entity to save
     * @return the persisted entity
     */
    SysOrderProduct save(SysOrderProduct sysOrderProduct);

    /**
     * Get all the sysOrderProducts.
     *
     * @return the list of entities
     */
    List<SysOrderProduct> findAll();


    /**
     * Get the "id" sysOrderProduct.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysOrderProduct> findOne(Long id);

    /**
     * Delete the "id" sysOrderProduct.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
