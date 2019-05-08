package com.ybb.mall.service;

import com.ybb.mall.domain.SysCouponClassify;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCouponClassify.
 */
public interface SysCouponClassifyService {

    /**
     * Save a sysCouponClassify.
     *
     * @param sysCouponClassify the entity to save
     * @return the persisted entity
     */
    SysCouponClassify save(SysCouponClassify sysCouponClassify);

    /**
     * Get all the sysCouponClassifies.
     *
     * @return the list of entities
     */
    List<SysCouponClassify> findAll();


    /**
     * Get the "id" sysCouponClassify.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCouponClassify> findOne(Long id);

    /**
     * Delete the "id" sysCouponClassify.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
