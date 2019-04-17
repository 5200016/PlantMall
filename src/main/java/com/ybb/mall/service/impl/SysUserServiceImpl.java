package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysUserService;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.SysUserRepository;
import com.ybb.mall.service.dto.user.UserListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysUser.
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private final SysUserRepository sysUserRepository;

    public SysUserServiceImpl(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    /**
     * Save a sysUser.
     *
     * @param sysUser the entity to save
     * @return the persisted entity
     */
    @Override
    public SysUser save(SysUser sysUser) {
        log.debug("Request to save SysUser : {}", sysUser);
        return sysUserRepository.save(sysUser);
    }

    /**
     * Get all the sysUsers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysUser> findAll() {
        log.debug("Request to get all SysUsers");
        return sysUserRepository.findAllWithEagerRelationships();
    }

    /**
     * Get all the SysUser with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<SysUser> findAllWithEagerRelationships(Pageable pageable) {
        return sysUserRepository.findAllWithEagerRelationships(pageable);
    }


    /**
     * Get one sysUser by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysUser> findOne(Long id) {
        log.debug("Request to get SysUser : {}", id);
        return sysUserRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the sysUser by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysUser : {}", id);
        sysUserRepository.deleteById(id);
    }

    @Override
    public Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize) {
        return sysUserRepository.findUserList(phone, PageRequest.of(pageNum, pageSize));
    }
}
