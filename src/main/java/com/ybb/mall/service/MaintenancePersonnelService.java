package com.ybb.mall.service;

import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.maintenance.FinishMaintenanceVM;

import java.util.List;
import java.util.Optional;

/**
 * 养护人员
 */
public interface MaintenancePersonnelService {

    /**
     * 查询养护人员列表
     * @return
     */
    List<SysMaintenancePersonnel> findMaintenancePersonnelList();
}
