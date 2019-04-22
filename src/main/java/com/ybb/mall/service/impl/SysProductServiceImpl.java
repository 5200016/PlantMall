package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysProductService;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.repository.SysProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysProduct.
 */
@Service
@Transactional
public class SysProductServiceImpl implements SysProductService {

    private final Logger log = LoggerFactory.getLogger(SysProductServiceImpl.class);

    private final SysProductRepository sysProductRepository;

    public SysProductServiceImpl(SysProductRepository sysProductRepository) {
        this.sysProductRepository = sysProductRepository;
    }

    /**
     * Save a sysProduct.
     *
     * @param sysProduct the entity to save
     * @return the persisted entity
     */
    @Override
    public SysProduct save(SysProduct sysProduct) {
        log.debug("Request to save SysProduct : {}", sysProduct);
        return sysProductRepository.save(sysProduct);
    }

    /**
     * Get all the sysProducts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysProduct> findAll() {
        log.debug("Request to get all SysProducts");
        return sysProductRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the SysProduct with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SysProduct> findAllWithEagerRelationships(Pageable pageable) {
        return sysProductRepository.findAllWithEagerRelationships(pageable);
    }
    

    /**
     * Get one sysProduct by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysProduct> findOne(Long id) {
        log.debug("Request to get SysProduct : {}", id);
        return sysProductRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the sysProduct by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysProduct : {}", id);
        sysProductRepository.deleteById(id);
    }
}
