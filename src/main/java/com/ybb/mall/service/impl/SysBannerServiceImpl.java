package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysBannerService;
import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.repository.SysBannerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysBanner.
 */
@Service
@Transactional
public class SysBannerServiceImpl implements SysBannerService {

    private final Logger log = LoggerFactory.getLogger(SysBannerServiceImpl.class);

    private final SysBannerRepository sysBannerRepository;

    public SysBannerServiceImpl(SysBannerRepository sysBannerRepository) {
        this.sysBannerRepository = sysBannerRepository;
    }

    /**
     * Save a sysBanner.
     *
     * @param sysBanner the entity to save
     * @return the persisted entity
     */
    @Override
    public SysBanner save(SysBanner sysBanner) {
        log.debug("Request to save SysBanner : {}", sysBanner);
        return sysBannerRepository.save(sysBanner);
    }

    /**
     * Get all the sysBanners.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysBanner> findAll() {
        log.debug("Request to get all SysBanners");
        return sysBannerRepository.findAll();
    }


    /**
     * Get one sysBanner by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysBanner> findOne(Long id) {
        log.debug("Request to get SysBanner : {}", id);
        return sysBannerRepository.findById(id);
    }

    /**
     * Delete the sysBanner by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysBanner : {}", id);
        sysBannerRepository.deleteById(id);
    }
}
