package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCollectionService;
import com.ybb.mall.domain.SysCollection;
import com.ybb.mall.repository.SysCollectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCollection.
 */
@Service
@Transactional
public class SysCollectionServiceImpl implements SysCollectionService {

    private final Logger log = LoggerFactory.getLogger(SysCollectionServiceImpl.class);

    private final SysCollectionRepository sysCollectionRepository;

    public SysCollectionServiceImpl(SysCollectionRepository sysCollectionRepository) {
        this.sysCollectionRepository = sysCollectionRepository;
    }

    /**
     * Save a sysCollection.
     *
     * @param sysCollection the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCollection save(SysCollection sysCollection) {
        log.debug("Request to save SysCollection : {}", sysCollection);
        return sysCollectionRepository.save(sysCollection);
    }

    /**
     * Get all the sysCollections.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCollection> findAll() {
        log.debug("Request to get all SysCollections");
        return sysCollectionRepository.findAll();
    }


    /**
     * Get one sysCollection by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCollection> findOne(Long id) {
        log.debug("Request to get SysCollection : {}", id);
        return sysCollectionRepository.findById(id);
    }

    /**
     * Delete the sysCollection by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCollection : {}", id);
        sysCollectionRepository.deleteById(id);
    }
}
