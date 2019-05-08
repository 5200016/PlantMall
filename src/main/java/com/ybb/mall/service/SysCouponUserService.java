package com.ybb.mall.service;

import com.ybb.mall.domain.SysCouponUser;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCouponUser.
 */
public interface SysCouponUserService {

    /**
     * Save a sysCouponUser.
     *
     * @param sysCouponUser the entity to save
     * @return the persisted entity
     */
    SysCouponUser save(SysCouponUser sysCouponUser);

    /**
     * Get all the sysCouponUsers.
     *
     * @return the list of entities
     */
    List<SysCouponUser> findAll();


    /**
     * Get the "id" sysCouponUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCouponUser> findOne(Long id);

    /**
     * Delete the "id" sysCouponUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
