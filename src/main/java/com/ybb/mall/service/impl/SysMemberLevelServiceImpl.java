package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysMemberLevelService;
import com.ybb.mall.domain.SysMemberLevel;
import com.ybb.mall.repository.SysMemberLevelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysMemberLevel.
 */
@Service
@Transactional
public class SysMemberLevelServiceImpl implements SysMemberLevelService {

    private final Logger log = LoggerFactory.getLogger(SysMemberLevelServiceImpl.class);

    private final SysMemberLevelRepository sysMemberLevelRepository;

    public SysMemberLevelServiceImpl(SysMemberLevelRepository sysMemberLevelRepository) {
        this.sysMemberLevelRepository = sysMemberLevelRepository;
    }

    /**
     * Save a sysMemberLevel.
     *
     * @param sysMemberLevel the entity to save
     * @return the persisted entity
     */
    @Override
    public SysMemberLevel save(SysMemberLevel sysMemberLevel) {
        log.debug("Request to save SysMemberLevel : {}", sysMemberLevel);
        return sysMemberLevelRepository.save(sysMemberLevel);
    }

    /**
     * Get all the sysMemberLevels.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysMemberLevel> findAll() {
        log.debug("Request to get all SysMemberLevels");
        return sysMemberLevelRepository.findAll();
    }


    /**
     * Get one sysMemberLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysMemberLevel> findOne(Long id) {
        log.debug("Request to get SysMemberLevel : {}", id);
        return sysMemberLevelRepository.findById(id);
    }

    /**
     * Delete the sysMemberLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysMemberLevel : {}", id);
        sysMemberLevelRepository.deleteById(id);
    }
}
