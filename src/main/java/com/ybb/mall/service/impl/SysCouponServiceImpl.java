package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCouponService;
import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.repository.SysCouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCoupon.
 */
@Service
@Transactional
public class SysCouponServiceImpl implements SysCouponService {

    private final Logger log = LoggerFactory.getLogger(SysCouponServiceImpl.class);

    private final SysCouponRepository sysCouponRepository;

    public SysCouponServiceImpl(SysCouponRepository sysCouponRepository) {
        this.sysCouponRepository = sysCouponRepository;
    }

    /**
     * Save a sysCoupon.
     *
     * @param sysCoupon the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCoupon save(SysCoupon sysCoupon) {
        log.debug("Request to save SysCoupon : {}", sysCoupon);
        return sysCouponRepository.save(sysCoupon);
    }

    /**
     * Get all the sysCoupons.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCoupon> findAll() {
        log.debug("Request to get all SysCoupons");
        return sysCouponRepository.findAll();
    }


    /**
     * Get one sysCoupon by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCoupon> findOne(Long id) {
        log.debug("Request to get SysCoupon : {}", id);
        return sysCouponRepository.findById(id);
    }

    /**
     * Delete the sysCoupon by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCoupon : {}", id);
        sysCouponRepository.deleteById(id);
    }
}
