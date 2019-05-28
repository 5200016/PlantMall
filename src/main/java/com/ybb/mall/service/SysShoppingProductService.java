package com.ybb.mall.service;

import com.ybb.mall.domain.SysShoppingProduct;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysShoppingProduct.
 */
public interface SysShoppingProductService {

    /**
     * Save a sysShoppingProduct.
     *
     * @param sysShoppingProduct the entity to save
     * @return the persisted entity
     */
    SysShoppingProduct save(SysShoppingProduct sysShoppingProduct);

    /**
     * Get all the sysShoppingProducts.
     *
     * @return the list of entities
     */
    List<SysShoppingProduct> findAll();


    /**
     * Get the "id" sysShoppingProduct.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysShoppingProduct> findOne(Long id);

    /**
     * Delete the "id" sysShoppingProduct.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
