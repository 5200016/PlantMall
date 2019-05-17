package com.ybb.mall.service;

import com.ybb.mall.service.dto.appointment.AppointmentDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.appointment.UpdateAppointmentStatusVM;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description : 预约管理
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public interface AppointmentService {

    /**
     * 分页查询预约列表
     */
    Page<AppointmentDTO> findAppointmentList(String value, Integer status, Integer pageNum, Integer pageSize);

    /**
     * 修改预约状态
     */
    ResultObj updateAppointmentStatusBatch(List<Long> id);

    /**
     * 分页查询预约列表
     */
    Page<AppointmentDTO> findAppointmentListByStatus(Integer status, String openid, Integer pageNum, Integer pageSize);

    /**
     * 修改预约订单状态
     */
    ResultObj updateAppointmentById(Long id, Integer status);
}
