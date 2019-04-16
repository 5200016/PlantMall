package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysClassifyService;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.repository.SysClassifyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysClassify.
 */
@Service
@Transactional
public class SysClassifyServiceImpl implements SysClassifyService {

    private final Logger log = LoggerFactory.getLogger(SysClassifyServiceImpl.class);

    private final SysClassifyRepository sysClassifyRepository;

    public SysClassifyServiceImpl(SysClassifyRepository sysClassifyRepository) {
        this.sysClassifyRepository = sysClassifyRepository;
    }

    /**
     * Save a sysClassify.
     *
     * @param sysClassify the entity to save
     * @return the persisted entity
     */
    @Override
    public SysClassify save(SysClassify sysClassify) {
        log.debug("Request to save SysClassify : {}", sysClassify);
        return sysClassifyRepository.save(sysClassify);
    }

    /**
     * Get all the sysClassifies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysClassify> findAll() {
        log.debug("Request to get all SysClassifies");
        return sysClassifyRepository.findAll();
    }


    /**
     * Get one sysClassify by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysClassify> findOne(Long id) {
        log.debug("Request to get SysClassify : {}", id);
        return sysClassifyRepository.findById(id);
    }

    /**
     * Delete the sysClassify by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysClassify : {}", id);
        sysClassifyRepository.deleteById(id);
    }
}
