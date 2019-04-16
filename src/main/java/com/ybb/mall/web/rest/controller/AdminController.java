package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.SysAdminService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.AdminVM;
import com.ybb.mall.web.rest.vm.LoginVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * @Description : 管理员账号信息管理
 * @Author 黄志成
 * @Date 2019-04-15
 * @Version
 */

@Api(description="管理员账号信息管理")
@RestController
@RequestMapping("/mall")
public class AdminController {

    private final SysAdminService sysAdminService;

    public AdminController(SysAdminService sysAdminService) {
        this.sysAdminService = sysAdminService;
    }

    /**
     * 用户登录
     * @throws URISyntaxException
     */
    @ApiOperation("用户登录 RequestBody")
    @PostMapping("/login")
    @Timed
    public ResultObj userLogin(@ApiParam(name="loginVM",value="用户登录实体",required=true) @RequestBody AdminVM admin) throws URISyntaxException {
        return sysAdminService.adminLogin(admin);
    }
}
