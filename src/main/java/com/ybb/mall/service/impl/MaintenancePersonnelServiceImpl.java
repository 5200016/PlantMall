package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.repository.MaintenancePersonnelRepository;
import com.ybb.mall.service.MaintenancePersonnelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public MaintenancePersonnelServiceImpl(MaintenancePersonnelRepository maintenancePersonnelRepository) {
        this.maintenancePersonnelRepository = maintenancePersonnelRepository;
    }

    @Override
    public List<SysMaintenancePersonnel> findMaintenancePersonnelList() {
        return maintenancePersonnelRepository.findAll();
    }
}
