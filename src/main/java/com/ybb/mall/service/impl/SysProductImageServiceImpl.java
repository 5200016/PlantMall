package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysProductImageService;
import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.repository.SysProductImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysProductImage.
 */
@Service
@Transactional
public class SysProductImageServiceImpl implements SysProductImageService {

    private final Logger log = LoggerFactory.getLogger(SysProductImageServiceImpl.class);

    private final SysProductImageRepository sysProductImageRepository;

    public SysProductImageServiceImpl(SysProductImageRepository sysProductImageRepository) {
        this.sysProductImageRepository = sysProductImageRepository;
    }

    /**
     * Save a sysProductImage.
     *
     * @param sysProductImage the entity to save
     * @return the persisted entity
     */
    @Override
    public SysProductImage save(SysProductImage sysProductImage) {
        log.debug("Request to save SysProductImage : {}", sysProductImage);
        return sysProductImageRepository.save(sysProductImage);
    }

    /**
     * Get all the sysProductImages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysProductImage> findAll() {
        log.debug("Request to get all SysProductImages");
        return sysProductImageRepository.findAll();
    }


    /**
     * Get one sysProductImage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysProductImage> findOne(Long id) {
        log.debug("Request to get SysProductImage : {}", id);
        return sysProductImageRepository.findById(id);
    }

    /**
     * Delete the sysProductImage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysProductImage : {}", id);
        sysProductImageRepository.deleteById(id);
    }
}
