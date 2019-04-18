package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.SysRechargeService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.recharge.InsertRechargeVM;
import com.ybb.mall.web.rest.vm.recharge.UpdateRechargeVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 充值项目管理
 * @Author 黄志成
 * @Date 2019-04-17
 * @Version
 */

@Api(description="充值项目管理")
@RestController
@RequestMapping("/mall")
public class RechargeController {
    private final SysRechargeService rechargeService;

    public RechargeController(SysRechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    /**
     * 充值项目列表查询（根据金额正序）
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("充值项目列表查询")
    @GetMapping("/recharge_list")
    @Timed
    public ResultObj selectRechargeList() throws URISyntaxException {
        return ResultObj.back(200, rechargeService.findRechargeList());
    }

    /**
     * 新增充值项目
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增充值项目")
    @PostMapping("/recharge")
    @Timed
    public ResultObj insertRecharge(@RequestBody InsertRechargeVM recharge) throws URISyntaxException {
        return rechargeService.insertRecharge(recharge);
    }

    /**
     * 修改充值项目
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改充值项目")
    @PutMapping("/recharge")
    @Timed
    public ResultObj updateRecharge(@RequestBody UpdateRechargeVM recharge) throws URISyntaxException {
        return rechargeService.updateRecharge(recharge);
    }

    /**
     * 删除充值项目
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除充值项目")
    @DeleteMapping("/recharge/{id}")
    @Timed
    public ResultObj deleteRecharge(@ApiParam(name="id",value="主键id",required=true) @PathVariable Long id) throws URISyntaxException {
        return rechargeService.deleteRecharge(id);
    }
}
