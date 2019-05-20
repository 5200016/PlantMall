package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.repository.CustomerServiceRepository;
import com.ybb.mall.service.CustomerService;
import com.ybb.mall.service.wx.WXCustomerService;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.customer.CustomerServiceVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description : 商城客服
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerServiceRepository customerServiceRepository;

    public CustomerServiceImpl(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    @Override
    public List<SysCustomerService> findAll() {
        return customerServiceRepository.findAll();
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
        customerServiceRepository.save(sysCustomerService);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }
}
