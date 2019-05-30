package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysFormService;
import com.ybb.mall.domain.SysForm;
import com.ybb.mall.repository.SysFormRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysForm.
 */
@Service
@Transactional
public class SysFormServiceImpl implements SysFormService {

    private final Logger log = LoggerFactory.getLogger(SysFormServiceImpl.class);

    private final SysFormRepository sysFormRepository;

    public SysFormServiceImpl(SysFormRepository sysFormRepository) {
        this.sysFormRepository = sysFormRepository;
    }

    /**
     * Save a sysForm.
     *
     * @param sysForm the entity to save
     * @return the persisted entity
     */
    @Override
    public SysForm save(SysForm sysForm) {
        log.debug("Request to save SysForm : {}", sysForm);
        return sysFormRepository.save(sysForm);
    }

    /**
     * Get all the sysForms.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysForm> findAll() {
        log.debug("Request to get all SysForms");
        return sysFormRepository.findAll();
    }


    /**
     * Get one sysForm by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysForm> findOne(Long id) {
        log.debug("Request to get SysForm : {}", id);
        return sysFormRepository.findById(id);
    }

    /**
     * Delete the sysForm by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysForm : {}", id);
        sysFormRepository.deleteById(id);
    }
}
