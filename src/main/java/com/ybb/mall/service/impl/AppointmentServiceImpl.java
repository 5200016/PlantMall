package com.ybb.mall.service.impl;

import com.ybb.mall.repository.AppointmentRepository;
import com.ybb.mall.service.AppointmentService;
import com.ybb.mall.service.dto.appointment.AppointmentDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.appointment.UpdateAppointmentStatusVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @Description : 预约管理
 * @Author 黄志成
 * @Date 2019-04-29
 * @Version
 */

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Page<AppointmentDTO> findAppointmentList(String value, Integer status, Integer pageNum, Integer pageSize) {
        Integer statusFlag = 0;
        if (!TypeUtils.isEmpty(status) && !status.equals(-1)) {
            statusFlag = 1;
        }
        return appointmentRepository.findAppointmentList(value, status, statusFlag, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj updateAppointmentStatusBatch(List<Long> id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backCRUDError("请未选择处理项");
        }

        for (Long data : id){
            appointmentRepository.updateAppointmentStatus(data, 1);
        }
        return ResultObj.backCRUDSuccess("处理成功");
    }
}
