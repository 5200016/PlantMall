package com.ybb.mall.service;

import com.ybb.mall.domain.SysUser;

import com.ybb.mall.service.dto.user.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysUser.
 */
public interface SysUserService {

    /**
     * Save a sysUser.
     *
     * @param sysUser the entity to save
     * @return the persisted entity
     */
    SysUser save(SysUser sysUser);

    /**
     * Get all the sysUsers.
     *
     * @return the list of entities
     */
    List<SysUser> findAll();

    /**
     * Get all the SysUser with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SysUser> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" sysUser.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysUser> findOne(Long id);

    /**
     * Delete the "id" sysUser.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 分页查询用户列表
     * @param phone
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize);
}
