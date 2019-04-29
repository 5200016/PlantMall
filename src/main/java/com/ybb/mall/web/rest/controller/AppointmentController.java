package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.AppointmentService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.appointment.UpdateAppointmentStatusVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @Description : 预约管理
 * @Author 黄志成
 * @Date 2019-04-29
 * @Version
 */

@Api(description="预约管理")
@RestController
@RequestMapping("/mall")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * 分页查询预约列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询预约列表")
    @GetMapping("/appointment")
    @Timed
    public ResultObj selectAppointmentList(@ApiParam(name = "status", value = "预约状态（0：未处理，1：已处理）", required = true) @RequestParam Integer status,
                                           @ApiParam(name = "value", value = "姓名、手机号", required = true) @RequestParam String value,
                                           @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                           @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, appointmentService.findAppointmentList(value, status, pageNum, pageSize));
    }

    /**
     * 批量修改预约记录状态
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("批量修改预约记录状态")
    @PutMapping("/appointment/status")
    @Timed
    public ResultObj updateAppointmentStatus(@RequestBody List<Long> id) throws URISyntaxException {
        return appointmentService.updateAppointmentStatusBatch(id);
    }
}
