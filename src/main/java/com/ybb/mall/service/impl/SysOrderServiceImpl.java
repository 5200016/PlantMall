package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysOrderService;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.repository.SysOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysOrder.
 */
@Service
@Transactional
public class SysOrderServiceImpl implements SysOrderService {

    private final Logger log = LoggerFactory.getLogger(SysOrderServiceImpl.class);

    private final SysOrderRepository sysOrderRepository;

    public SysOrderServiceImpl(SysOrderRepository sysOrderRepository) {
        this.sysOrderRepository = sysOrderRepository;
    }

    /**
     * Save a sysOrder.
     *
     * @param sysOrder the entity to save
     * @return the persisted entity
     */
    @Override
    public SysOrder save(SysOrder sysOrder) {
        log.debug("Request to save SysOrder : {}", sysOrder);
        return sysOrderRepository.save(sysOrder);
    }

    /**
     * Get all the sysOrders.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysOrder> findAll() {
        log.debug("Request to get all SysOrders");
        return sysOrderRepository.findAll();
    }


    /**
     * Get one sysOrder by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysOrder> findOne(Long id) {
        log.debug("Request to get SysOrder : {}", id);
        return sysOrderRepository.findById(id);
    }

    /**
     * Delete the sysOrder by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysOrder : {}", id);
        sysOrderRepository.deleteById(id);
    }
}
