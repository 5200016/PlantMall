package com.ybb.mall.service;

import com.ybb.mall.domain.SysOrder;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysOrder.
 */
public interface SysOrderService {

    /**
     * Save a sysOrder.
     *
     * @param sysOrder the entity to save
     * @return the persisted entity
     */
    SysOrder save(SysOrder sysOrder);

    /**
     * Get all the sysOrders.
     *
     * @return the list of entities
     */
    List<SysOrder> findAll();


    /**
     * Get the "id" sysOrder.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysOrder> findOne(Long id);

    /**
     * Delete the "id" sysOrder.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
