package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysAdminService;
import com.ybb.mall.domain.SysAdmin;
import com.ybb.mall.repository.SysAdminRepository;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.AdminVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysAdmin.
 */
@Service
@Transactional
public class SysAdminServiceImpl implements SysAdminService {

    private final Logger log = LoggerFactory.getLogger(SysAdminServiceImpl.class);

    private final SysAdminRepository sysAdminRepository;
    private final PasswordEncoder passwordEncoder;

    public SysAdminServiceImpl(SysAdminRepository sysAdminRepository, PasswordEncoder passwordEncoder) {
        this.sysAdminRepository = sysAdminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Save a sysAdmin.
     *
     * @param sysAdmin the entity to save
     * @return the persisted entity
     */
    @Override
    public SysAdmin save(SysAdmin sysAdmin) {
        log.debug("Request to save SysAdmin : {}", sysAdmin);
        return sysAdminRepository.save(sysAdmin);
    }

    /**
     * Get all the sysAdmins.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysAdmin> findAll() {
        log.debug("Request to get all SysAdmins");
        return sysAdminRepository.findAll();
    }


    /**
     * Get one sysAdmin by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysAdmin> findOne(Long id) {
        log.debug("Request to get SysAdmin : {}", id);
        return sysAdminRepository.findById(id);
    }

    /**
     * Delete the sysAdmin by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysAdmin : {}", id);
        sysAdminRepository.deleteById(id);
    }

    /**
     * 管理员登陆验证
     * @param admin
     * @return
     */
    @Override
    public ResultObj adminLogin(AdminVM admin) {
        SysAdmin sysAdmin = sysAdminRepository.findSysAdminByUsername(admin.getUsername());
        if(TypeUtils.isEmpty(sysAdmin)){
            return ResultObj.backInfo(false, 200, "用户不存在", null);
        }
        if(passwordEncoder.matches(admin.getPassword(), sysAdmin.getPassword())){
            return ResultObj.backInfo(true, 200, "登录成功", null);
        }
        return ResultObj.backInfo(false, 200, "密码不正确", null);
    }
}
