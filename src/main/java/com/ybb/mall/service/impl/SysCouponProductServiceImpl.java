package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCouponProductService;
import com.ybb.mall.domain.SysCouponProduct;
import com.ybb.mall.repository.SysCouponProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCouponProduct.
 */
@Service
@Transactional
public class SysCouponProductServiceImpl implements SysCouponProductService {

    private final Logger log = LoggerFactory.getLogger(SysCouponProductServiceImpl.class);

    private final SysCouponProductRepository sysCouponProductRepository;

    public SysCouponProductServiceImpl(SysCouponProductRepository sysCouponProductRepository) {
        this.sysCouponProductRepository = sysCouponProductRepository;
    }

    /**
     * Save a sysCouponProduct.
     *
     * @param sysCouponProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCouponProduct save(SysCouponProduct sysCouponProduct) {
        log.debug("Request to save SysCouponProduct : {}", sysCouponProduct);
        return sysCouponProductRepository.save(sysCouponProduct);
    }

    /**
     * Get all the sysCouponProducts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCouponProduct> findAll() {
        log.debug("Request to get all SysCouponProducts");
        return sysCouponProductRepository.findAll();
    }


    /**
     * Get one sysCouponProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCouponProduct> findOne(Long id) {
        log.debug("Request to get SysCouponProduct : {}", id);
        return sysCouponProductRepository.findById(id);
    }

    /**
     * Delete the sysCouponProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCouponProduct : {}", id);
        sysCouponProductRepository.deleteById(id);
    }
}
