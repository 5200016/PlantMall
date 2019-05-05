package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysMaintenancePersonnelService;
import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.repository.SysMaintenancePersonnelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysMaintenancePersonnel.
 */
@Service
@Transactional
public class SysMaintenancePersonnelServiceImpl implements SysMaintenancePersonnelService {

    private final Logger log = LoggerFactory.getLogger(SysMaintenancePersonnelServiceImpl.class);

    private final SysMaintenancePersonnelRepository sysMaintenancePersonnelRepository;

    public SysMaintenancePersonnelServiceImpl(SysMaintenancePersonnelRepository sysMaintenancePersonnelRepository) {
        this.sysMaintenancePersonnelRepository = sysMaintenancePersonnelRepository;
    }

    /**
     * Save a sysMaintenancePersonnel.
     *
     * @param sysMaintenancePersonnel the entity to save
     * @return the persisted entity
     */
    @Override
    public SysMaintenancePersonnel save(SysMaintenancePersonnel sysMaintenancePersonnel) {
        log.debug("Request to save SysMaintenancePersonnel : {}", sysMaintenancePersonnel);
        return sysMaintenancePersonnelRepository.save(sysMaintenancePersonnel);
    }

    /**
     * Get all the sysMaintenancePersonnels.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysMaintenancePersonnel> findAll() {
        log.debug("Request to get all SysMaintenancePersonnels");
        return sysMaintenancePersonnelRepository.findAll();
    }


    /**
     * Get one sysMaintenancePersonnel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMaintenancePersonnel> findOne(Long id) {
        log.debug("Request to get SysMaintenancePersonnel : {}", id);
        return sysMaintenancePersonnelRepository.findById(id);
    }

    /**
     * Delete the sysMaintenancePersonnel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMaintenancePersonnel : {}", id);
        sysMaintenancePersonnelRepository.deleteById(id);
    }
}
