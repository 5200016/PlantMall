package com.ybb.mall.service;

import com.ybb.mall.domain.SysCouponProduct;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCouponProduct.
 */
public interface SysCouponProductService {

    /**
     * Save a sysCouponProduct.
     *
     * @param sysCouponProduct the entity to save
     * @return the persisted entity
     */
    SysCouponProduct save(SysCouponProduct sysCouponProduct);

    /**
     * Get all the sysCouponProducts.
     *
     * @return the list of entities
     */
    List<SysCouponProduct> findAll();


    /**
     * Get the "id" sysCouponProduct.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCouponProduct> findOne(Long id);

    /**
     * Delete the "id" sysCouponProduct.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
