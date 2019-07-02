package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.repository.MaintenanceFinishRepository;
import com.ybb.mall.repository.MaintenancePersonnelRepository;
import com.ybb.mall.service.MaintenancePersonnelService;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.maintenance.FinishMaintenanceVM;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 养护人员
 */
@Service
@Transactional
public class MaintenancePersonnelServiceImpl implements MaintenancePersonnelService {

    private final MaintenancePersonnelRepository maintenancePersonnelRepository;

    private final MaintenanceFinishRepository maintenanceFinishRepository;

    public MaintenancePersonnelServiceImpl(MaintenancePersonnelRepository maintenancePersonnelRepository, MaintenanceFinishRepository maintenanceFinishRepository) {
        this.maintenancePersonnelRepository = maintenancePersonnelRepository;
        this.maintenanceFinishRepository = maintenanceFinishRepository;
    }

    @Override
    public List<SysMaintenancePersonnel> findMaintenancePersonnelList() {
        return maintenancePersonnelRepository.findAll();
    }

    @Override
    public ResultObj insertMaintenanceTime(FinishMaintenanceVM finishMaintenance) {
        SysMaintenanceFinish maintenanceFinish = new SysMaintenanceFinish();
        maintenanceFinish.setTime(finishMaintenance.getTime());
        maintenanceFinish.setUrl(finishMaintenance.getUrl());
        maintenanceFinish.setCreateTime(DateUtil.getZoneDateTime());
        maintenanceFinish.setUpdateTime(DateUtil.getZoneDateTime());

        SysOrder order = new SysOrder();
        order.setId(finishMaintenance.getOrderId());
        maintenanceFinish.setOrder(order);
        maintenanceFinishRepository.save(maintenanceFinish);
        return ResultObj.backCRUDSuccess("新增成功");
    }
}
