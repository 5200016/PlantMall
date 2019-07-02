package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysMaintenanceFinishService;
import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.repository.SysMaintenanceFinishRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysMaintenanceFinish.
 */
@Service
@Transactional
public class SysMaintenanceFinishServiceImpl implements SysMaintenanceFinishService {

    private final Logger log = LoggerFactory.getLogger(SysMaintenanceFinishServiceImpl.class);

    private final SysMaintenanceFinishRepository sysMaintenanceFinishRepository;

    public SysMaintenanceFinishServiceImpl(SysMaintenanceFinishRepository sysMaintenanceFinishRepository) {
        this.sysMaintenanceFinishRepository = sysMaintenanceFinishRepository;
    }

    /**
     * Save a sysMaintenanceFinish.
     *
     * @param sysMaintenanceFinish the entity to save
     * @return the persisted entity
     */
    @Override
    public SysMaintenanceFinish save(SysMaintenanceFinish sysMaintenanceFinish) {
        log.debug("Request to save SysMaintenanceFinish : {}", sysMaintenanceFinish);
        return sysMaintenanceFinishRepository.save(sysMaintenanceFinish);
    }

    /**
     * Get all the sysMaintenanceFinishes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysMaintenanceFinish> findAll() {
        log.debug("Request to get all SysMaintenanceFinishes");
        return sysMaintenanceFinishRepository.findAll();
    }


    /**
     * Get one sysMaintenanceFinish by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMaintenanceFinish> findOne(Long id) {
        log.debug("Request to get SysMaintenanceFinish : {}", id);
        return sysMaintenanceFinishRepository.findById(id);
    }

    /**
     * Delete the sysMaintenanceFinish by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMaintenanceFinish : {}", id);
        sysMaintenanceFinishRepository.deleteById(id);
    }
}
