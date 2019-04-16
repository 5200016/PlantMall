package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysMemberSettingService;
import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.repository.SysMemberSettingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysMemberSetting.
 */
@Service
@Transactional
public class SysMemberSettingServiceImpl implements SysMemberSettingService {

    private final Logger log = LoggerFactory.getLogger(SysMemberSettingServiceImpl.class);

    private final SysMemberSettingRepository sysMemberSettingRepository;

    public SysMemberSettingServiceImpl(SysMemberSettingRepository sysMemberSettingRepository) {
        this.sysMemberSettingRepository = sysMemberSettingRepository;
    }

    /**
     * Save a sysMemberSetting.
     *
     * @param sysMemberSetting the entity to save
     * @return the persisted entity
     */
    @Override
    public SysMemberSetting save(SysMemberSetting sysMemberSetting) {
        log.debug("Request to save SysMemberSetting : {}", sysMemberSetting);
        return sysMemberSettingRepository.save(sysMemberSetting);
    }

    /**
     * Get all the sysMemberSettings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysMemberSetting> findAll() {
        log.debug("Request to get all SysMemberSettings");
        return sysMemberSettingRepository.findAll();
    }


    /**
     * Get one sysMemberSetting by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMemberSetting> findOne(Long id) {
        log.debug("Request to get SysMemberSetting : {}", id);
        return sysMemberSettingRepository.findById(id);
    }

    /**
     * Delete the sysMemberSetting by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMemberSetting : {}", id);
        sysMemberSettingRepository.deleteById(id);
    }
}
