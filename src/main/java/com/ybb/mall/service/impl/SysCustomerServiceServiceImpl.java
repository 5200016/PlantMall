package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysCustomerServiceService;
import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.repository.SysCustomerServiceRepository;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.customer.CustomerServiceVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysCustomerService.
 */
@Service
@Transactional
public class SysCustomerServiceServiceImpl implements SysCustomerServiceService {

    private final Logger log = LoggerFactory.getLogger(SysCustomerServiceServiceImpl.class);

    private final SysCustomerServiceRepository sysCustomerServiceRepository;

    public SysCustomerServiceServiceImpl(SysCustomerServiceRepository sysCustomerServiceRepository) {
        this.sysCustomerServiceRepository = sysCustomerServiceRepository;
    }

    /**
     * Save a sysCustomerService.
     *
     * @param sysCustomerService the entity to save
     * @return the persisted entity
     */
    @Override
    public SysCustomerService save(SysCustomerService sysCustomerService) {
        log.debug("Request to save SysCustomerService : {}", sysCustomerService);
        return sysCustomerServiceRepository.save(sysCustomerService);
    }

    /**
     * Get all the sysCustomerServices.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysCustomerService> findAll() {
        log.debug("Request to get all SysCustomerServices");
        return sysCustomerServiceRepository.findAll();
    }


    /**
     * Get one sysCustomerService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysCustomerService> findOne(Long id) {
        log.debug("Request to get SysCustomerService : {}", id);
        return sysCustomerServiceRepository.findById(id);
    }

    /**
     * Delete the sysCustomerService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysCustomerService : {}", id);
        sysCustomerServiceRepository.deleteById(id);
    }

    @Override
    public ResultObj updateCustomerService(CustomerServiceVM customerService) {
        if(TypeUtils.isEmpty(customerService.getId())){
            return ResultObj.backInfo(false, 200, "编辑失败（id不能为空）", null);
        }
        SysCustomerService sysCustomerService = new SysCustomerService();
        sysCustomerService.setId(customerService.getId());
        sysCustomerService.setPhone(customerService.getPhone());
        sysCustomerService.setAddress(customerService.getAddress());
        sysCustomerService.setEmail(customerService.getEmail());
        sysCustomerService.setUpdateTime(DateUtil.getZoneDateTime());
        sysCustomerService.setCreateTime(customerService.getCreateTime());
        sysCustomerServiceRepository.save(sysCustomerService);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }
}
