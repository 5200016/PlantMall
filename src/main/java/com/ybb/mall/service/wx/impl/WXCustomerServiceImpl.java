package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.repository.CustomerServiceRepository;
import com.ybb.mall.service.wx.WXCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description : 微信小程序-商城客服
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

@Service
@Transactional
public class WXCustomerServiceImpl implements WXCustomerService {

    private final CustomerServiceRepository customerServiceRepository;

    public WXCustomerServiceImpl(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    @Override
    public List<SysCustomerService> findAll() {
        return customerServiceRepository.findAll();
    }
}
