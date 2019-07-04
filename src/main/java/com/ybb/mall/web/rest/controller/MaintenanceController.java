package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.MaintenancePersonnelService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.maintenance.FinishMaintenanceVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 绿植养护管理
 * @Author 黄志成
 * @Date 2019-05-05
 * @Version
 */

@Api(description="绿植养护管理")
@RestController
@RequestMapping("/mall")
public class MaintenanceController {
    private final MaintenancePersonnelService maintenancePersonnelService;

    public MaintenanceController(MaintenancePersonnelService maintenancePersonnelService) {
        this.maintenancePersonnelService = maintenancePersonnelService;
    }

    /**
     * 查询养护人员列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询养护人员列表")
    @GetMapping("/maintenance")
    @Timed
    public ResultObj selectMaintenanceList() throws URISyntaxException {
        return ResultObj.back(200, maintenancePersonnelService.findMaintenancePersonnelList());
    }


}
