package com.ybb.mall.service;

import com.ybb.mall.domain.SysReceiverAddress;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
     * Get all the SysReceiverAddress with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SysReceiverAddress> findAllWithEagerRelationships(Pageable pageable);
    
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
