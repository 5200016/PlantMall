package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysShoppingProductService;
import com.ybb.mall.domain.SysShoppingProduct;
import com.ybb.mall.repository.SysShoppingProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysShoppingProduct.
 */
@Service
@Transactional
public class SysShoppingProductServiceImpl implements SysShoppingProductService {

    private final Logger log = LoggerFactory.getLogger(SysShoppingProductServiceImpl.class);

    private final SysShoppingProductRepository sysShoppingProductRepository;

    public SysShoppingProductServiceImpl(SysShoppingProductRepository sysShoppingProductRepository) {
        this.sysShoppingProductRepository = sysShoppingProductRepository;
    }

    /**
     * Save a sysShoppingProduct.
     *
     * @param sysShoppingProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public SysShoppingProduct save(SysShoppingProduct sysShoppingProduct) {
        log.debug("Request to save SysShoppingProduct : {}", sysShoppingProduct);
        return sysShoppingProductRepository.save(sysShoppingProduct);
    }

    /**
     * Get all the sysShoppingProducts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysShoppingProduct> findAll() {
        log.debug("Request to get all SysShoppingProducts");
        return sysShoppingProductRepository.findAll();
    }


    /**
     * Get one sysShoppingProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysShoppingProduct> findOne(Long id) {
        log.debug("Request to get SysShoppingProduct : {}", id);
        return sysShoppingProductRepository.findById(id);
    }

    /**
     * Delete the sysShoppingProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysShoppingProduct : {}", id);
        sysShoppingProductRepository.deleteById(id);
    }
}
