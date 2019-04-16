package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.SysMemberLevelService;
import com.ybb.mall.service.SysMemberSettingService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.AdminVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 会员等级设置
 * @Author 黄志成
 * @Date 2019-04-15
 * @Version
 */

@Api(description="会员等级设置")
@RestController
@RequestMapping("/mall")
public class MemberLevelController {

    private final SysMemberLevelService memberLevelService;

    private final SysMemberSettingService memberSettingService;

    public MemberLevelController(SysMemberLevelService memberLevelService, SysMemberSettingService memberSettingService) {
        this.memberLevelService = memberLevelService;
        this.memberSettingService = memberSettingService;
    }

    /**
     * 会员等级列表查询
     */
    @ApiOperation("会员等级列表查询")
    @GetMapping("/member_level")
    @Timed
    public ResultObj selectMemberLevel() throws URISyntaxException {
        return null;
    }

    /**
     * 会员等级列表修改
     */
    @ApiOperation("会员等级列表修改")
    @PutMapping("/member_level")
    @Timed
    public ResultObj updateMemberLevel() throws URISyntaxException {
        return null;
    }

    /**
     * 会员体系参数查询
     */
    @ApiOperation("会员体系参数查询")
    @GetMapping("/member_setting")
    @Timed
    public ResultObj selectMemberSetting() throws URISyntaxException {
        return null;
    }

    /**
     * 会员体系参数修改
     */
    @ApiOperation("会员等级列表查询")
    @PutMapping("/member_setting")
    @Timed
    public ResultObj updateMemberSetting() throws URISyntaxException {
        return null;
    }
}
