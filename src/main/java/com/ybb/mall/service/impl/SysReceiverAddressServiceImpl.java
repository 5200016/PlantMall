package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysReceiverAddressService;
import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.repository.SysReceiverAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysReceiverAddress.
 */
@Service
@Transactional
public class SysReceiverAddressServiceImpl implements SysReceiverAddressService {

    private final Logger log = LoggerFactory.getLogger(SysReceiverAddressServiceImpl.class);

    private final SysReceiverAddressRepository sysReceiverAddressRepository;

    public SysReceiverAddressServiceImpl(SysReceiverAddressRepository sysReceiverAddressRepository) {
        this.sysReceiverAddressRepository = sysReceiverAddressRepository;
    }

    /**
     * Save a sysReceiverAddress.
     *
     * @param sysReceiverAddress the entity to save
     * @return the persisted entity
     */
    @Override
    public SysReceiverAddress save(SysReceiverAddress sysReceiverAddress) {
        log.debug("Request to save SysReceiverAddress : {}", sysReceiverAddress);
        return sysReceiverAddressRepository.save(sysReceiverAddress);
    }

    /**
     * Get all the sysReceiverAddresses.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysReceiverAddress> findAll() {
        log.debug("Request to get all SysReceiverAddresses");
        return sysReceiverAddressRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the SysReceiverAddress with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SysReceiverAddress> findAllWithEagerRelationships(Pageable pageable) {
        return sysReceiverAddressRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one sysReceiverAddress by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysReceiverAddress> findOne(Long id) {
        log.debug("Request to get SysReceiverAddress : {}", id);
        return sysReceiverAddressRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the sysReceiverAddress by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysReceiverAddress : {}", id);
        sysReceiverAddressRepository.deleteById(id);
    }
}
