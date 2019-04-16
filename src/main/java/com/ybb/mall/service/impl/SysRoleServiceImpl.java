package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysRoleService;
import com.ybb.mall.domain.SysRole;
import com.ybb.mall.repository.SysRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysRole.
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    private final Logger log = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    private final SysRoleRepository sysRoleRepository;

    public SysRoleServiceImpl(SysRoleRepository sysRoleRepository) {
        this.sysRoleRepository = sysRoleRepository;
    }

    /**
     * Save a sysRole.
     *
     * @param sysRole the entity to save
     * @return the persisted entity
     */
    @Override
    public SysRole save(SysRole sysRole) {
        log.debug("Request to save SysRole : {}", sysRole);
        return sysRoleRepository.save(sysRole);
    }

    /**
     * Get all the sysRoles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysRole> findAll() {
        log.debug("Request to get all SysRoles");
        return sysRoleRepository.findAll();
    }


    /**
     * Get one sysRole by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysRole> findOne(Long id) {
        log.debug("Request to get SysRole : {}", id);
        return sysRoleRepository.findById(id);
    }

    /**
     * Delete the sysRole by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysRole : {}", id);
        sysRoleRepository.deleteById(id);
    }
}
