package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCouponUserService;
import com.ybb.mall.domain.SysCouponUser;
import com.ybb.mall.repository.SysCouponUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCouponUser.
 */
@Service
@Transactional
public class SysCouponUserServiceImpl implements SysCouponUserService {

    private final Logger log = LoggerFactory.getLogger(SysCouponUserServiceImpl.class);

    private final SysCouponUserRepository sysCouponUserRepository;

    public SysCouponUserServiceImpl(SysCouponUserRepository sysCouponUserRepository) {
        this.sysCouponUserRepository = sysCouponUserRepository;
    }

    /**
     * Save a sysCouponUser.
     *
     * @param sysCouponUser the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCouponUser save(SysCouponUser sysCouponUser) {
        log.debug("Request to save SysCouponUser : {}", sysCouponUser);
        return sysCouponUserRepository.save(sysCouponUser);
    }

    /**
     * Get all the sysCouponUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCouponUser> findAll() {
        log.debug("Request to get all SysCouponUsers");
        return sysCouponUserRepository.findAll();
    }


    /**
     * Get one sysCouponUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCouponUser> findOne(Long id) {
        log.debug("Request to get SysCouponUser : {}", id);
        return sysCouponUserRepository.findById(id);
    }

    /**
     * Delete the sysCouponUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCouponUser : {}", id);
        sysCouponUserRepository.deleteById(id);
    }
}
