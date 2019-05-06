package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysModuleService;
import com.ybb.mall.domain.SysModule;
import com.ybb.mall.repository.SysModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysModule.
 */
@Service
@Transactional
public class SysModuleServiceImpl implements SysModuleService {

    private final Logger log = LoggerFactory.getLogger(SysModuleServiceImpl.class);

    private final SysModuleRepository sysModuleRepository;

    public SysModuleServiceImpl(SysModuleRepository sysModuleRepository) {
        this.sysModuleRepository = sysModuleRepository;
    }

    /**
     * Save a sysModule.
     *
     * @param sysModule the entity to save
     * @return the persisted entity
     */
    @Override
    public SysModule save(SysModule sysModule) {
        log.debug("Request to save SysModule : {}", sysModule);
        return sysModuleRepository.save(sysModule);
    }

    /**
     * Get all the sysModules.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysModule> findAll() {
        log.debug("Request to get all SysModules");
        return sysModuleRepository.findAll();
    }


    /**
     * Get one sysModule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysModule> findOne(Long id) {
        log.debug("Request to get SysModule : {}", id);
        return sysModuleRepository.findById(id);
    }

    /**
     * Delete the sysModule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysModule : {}", id);
        sysModuleRepository.deleteById(id);
    }
}
