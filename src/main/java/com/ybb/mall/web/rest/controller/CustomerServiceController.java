package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.CustomerService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.customer.CustomerServiceVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 商城客服
 * @Author 黄志成
 * @Date 2019-04-18
 * @Version
 */

@Api(description="商城客服")
@RestController
@RequestMapping("/mall")
public class CustomerServiceController {
    private final CustomerService customerService;

    public CustomerServiceController(CustomerService customerService) {
        this.customerService = customerService;
    }


    /**
     * 商城客服设置查询
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("商城客服设置查询")
    @GetMapping("/customer_service")
    @Timed
    public ResultObj selectCustomerService() throws URISyntaxException {
        return ResultObj.back(200, customerService.findAll().get(0));
    }

    /**
     * 商城客服设置编辑
     * @param customerServiceVM
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("商城客服设置编辑")
    @PutMapping("/customer_service")
    @Timed
    public ResultObj updateCustomerService(@RequestBody CustomerServiceVM customerServiceVM) throws URISyntaxException {
        return customerService.updateCustomerService(customerServiceVM);
    }
}
