package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCouponClassifyService;
import com.ybb.mall.domain.SysCouponClassify;
import com.ybb.mall.repository.SysCouponClassifyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCouponClassify.
 */
@Service
@Transactional
public class SysCouponClassifyServiceImpl implements SysCouponClassifyService {

    private final Logger log = LoggerFactory.getLogger(SysCouponClassifyServiceImpl.class);

    private final SysCouponClassifyRepository sysCouponClassifyRepository;

    public SysCouponClassifyServiceImpl(SysCouponClassifyRepository sysCouponClassifyRepository) {
        this.sysCouponClassifyRepository = sysCouponClassifyRepository;
    }

    /**
     * Save a sysCouponClassify.
     *
     * @param sysCouponClassify the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCouponClassify save(SysCouponClassify sysCouponClassify) {
        log.debug("Request to save SysCouponClassify : {}", sysCouponClassify);
        return sysCouponClassifyRepository.save(sysCouponClassify);
    }

    /**
     * Get all the sysCouponClassifies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCouponClassify> findAll() {
        log.debug("Request to get all SysCouponClassifies");
        return sysCouponClassifyRepository.findAll();
    }


    /**
     * Get one sysCouponClassify by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCouponClassify> findOne(Long id) {
        log.debug("Request to get SysCouponClassify : {}", id);
        return sysCouponClassifyRepository.findById(id);
    }

    /**
     * Delete the sysCouponClassify by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCouponClassify : {}", id);
        sysCouponClassifyRepository.deleteById(id);
    }
}
