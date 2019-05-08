package com.ybb.mall.service;

import com.ybb.mall.domain.SysCoupon;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCoupon.
 */
public interface SysCouponService {

    /**
     * Save a sysCoupon.
     *
     * @param sysCoupon the entity to save
     * @return the persisted entity
     */
    SysCoupon save(SysCoupon sysCoupon);

    /**
     * Get all the sysCoupons.
     *
     * @return the list of entities
     */
    List<SysCoupon> findAll();


    /**
     * Get the "id" sysCoupon.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCoupon> findOne(Long id);

    /**
     * Delete the "id" sysCoupon.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
