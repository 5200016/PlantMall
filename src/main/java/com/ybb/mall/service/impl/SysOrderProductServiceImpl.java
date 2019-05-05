package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysOrderProductService;
import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.repository.SysOrderProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysOrderProduct.
 */
@Service
@Transactional
public class SysOrderProductServiceImpl implements SysOrderProductService {

    private final Logger log = LoggerFactory.getLogger(SysOrderProductServiceImpl.class);

    private final SysOrderProductRepository sysOrderProductRepository;

    public SysOrderProductServiceImpl(SysOrderProductRepository sysOrderProductRepository) {
        this.sysOrderProductRepository = sysOrderProductRepository;
    }

    /**
     * Save a sysOrderProduct.
     *
     * @param sysOrderProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public SysOrderProduct save(SysOrderProduct sysOrderProduct) {
        log.debug("Request to save SysOrderProduct : {}", sysOrderProduct);
        return sysOrderProductRepository.save(sysOrderProduct);
    }

    /**
     * Get all the sysOrderProducts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysOrderProduct> findAll() {
        log.debug("Request to get all SysOrderProducts");
        return sysOrderProductRepository.findAll();
    }


    /**
     * Get one sysOrderProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysOrderProduct> findOne(Long id) {
        log.debug("Request to get SysOrderProduct : {}", id);
        return sysOrderProductRepository.findById(id);
    }

    /**
     * Delete the sysOrderProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysOrderProduct : {}", id);
        sysOrderProductRepository.deleteById(id);
    }
}
