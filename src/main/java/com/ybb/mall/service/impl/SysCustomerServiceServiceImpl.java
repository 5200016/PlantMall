package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCustomerServiceService;
import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.repository.SysCustomerServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCustomerService.
 */
@Service
@Transactional
public class SysCustomerServiceServiceImpl implements SysCustomerServiceService {

    private final Logger log = LoggerFactory.getLogger(SysCustomerServiceServiceImpl.class);

    private final SysCustomerServiceRepository sysCustomerServiceRepository;

    public SysCustomerServiceServiceImpl(SysCustomerServiceRepository sysCustomerServiceRepository) {
        this.sysCustomerServiceRepository = sysCustomerServiceRepository;
    }

    /**
     * Save a sysCustomerService.
     *
     * @param sysCustomerService the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCustomerService save(SysCustomerService sysCustomerService) {
        log.debug("Request to save SysCustomerService : {}", sysCustomerService);
        return sysCustomerServiceRepository.save(sysCustomerService);
    }

    /**
     * Get all the sysCustomerServices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCustomerService> findAll() {
        log.debug("Request to get all SysCustomerServices");
        return sysCustomerServiceRepository.findAll();
    }


    /**
     * Get one sysCustomerService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCustomerService> findOne(Long id) {
        log.debug("Request to get SysCustomerService : {}", id);
        return sysCustomerServiceRepository.findById(id);
    }

    /**
     * Delete the sysCustomerService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCustomerService : {}", id);
        sysCustomerServiceRepository.deleteById(id);
    }
}
