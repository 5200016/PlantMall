package com.ybb.mall.service;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.customer.CustomerServiceVM;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysCustomerService.
 */
public interface SysCustomerServiceService {

    /**
     * Save a sysCustomerService.
     *
     * @param sysCustomerService the entity to save
     * @return the persisted entity
     */
    SysCustomerService save(SysCustomerService sysCustomerService);

    /**
     * Get all the sysCustomerServices.
     *
     * @return the list of entities
     */
    List<SysCustomerService> findAll();


    /**
     * Get the "id" sysCustomerService.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysCustomerService> findOne(Long id);

    /**
     * Delete the "id" sysCustomerService.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 编辑商城客服设置
     * @param customerService
     * @return
     */
    ResultObj updateCustomerService(CustomerServiceVM customerService);
}
