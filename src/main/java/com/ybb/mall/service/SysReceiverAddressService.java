package com.ybb.mall.service;

import com.ybb.mall.domain.SysReceiverAddress;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysReceiverAddress.
 */
public interface SysReceiverAddressService {

    /**
     * Save a sysReceiverAddress.
     *
     * @param sysReceiverAddress the entity to save
     * @return the persisted entity
     */
    SysReceiverAddress save(SysReceiverAddress sysReceiverAddress);

    /**
     * Get all the sysReceiverAddresses.
     *
     * @return the list of entities
     */
    List<SysReceiverAddress> findAll();


    /**
     * Get the "id" sysReceiverAddress.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysReceiverAddress> findOne(Long id);

    /**
     * Delete the "id" sysReceiverAddress.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
