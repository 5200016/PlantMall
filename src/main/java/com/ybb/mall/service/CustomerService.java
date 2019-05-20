package com.ybb.mall.service;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.customer.CustomerServiceVM;

import java.util.List;

/**
 * @Description : 商城客服
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

public interface CustomerService {

    /**
     * 查询商城客服设置
     * @return
     */
    List<SysCustomerService> findAll();

    /**
     * 编辑商城客服设置
     * @param customerService
     * @return
     */
    ResultObj updateCustomerService(CustomerServiceVM customerService);
}
