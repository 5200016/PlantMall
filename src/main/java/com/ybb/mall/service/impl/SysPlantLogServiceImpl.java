package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysPlantLogService;
import com.ybb.mall.domain.SysPlantLog;
import com.ybb.mall.repository.SysPlantLogRepository;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.product.plant.InsertPlantLogVM;
import com.ybb.mall.web.rest.vm.product.plant.UpdatePlantLogVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysPlantLog.
 */
@Service
@Transactional
public class SysPlantLogServiceImpl implements SysPlantLogService {

    private final Logger log = LoggerFactory.getLogger(SysPlantLogServiceImpl.class);

    private final SysPlantLogRepository sysPlantLogRepository;

    public SysPlantLogServiceImpl(SysPlantLogRepository sysPlantLogRepository) {
        this.sysPlantLogRepository = sysPlantLogRepository;
    }

    /**
     * Save a sysPlantLog.
     *
     * @param sysPlantLog the entity to save
     * @return the persisted entity
     */
    @Override
    public SysPlantLog save(SysPlantLog sysPlantLog) {
        log.debug("Request to save SysPlantLog : {}", sysPlantLog);
        return sysPlantLogRepository.save(sysPlantLog);
    }

    /**
     * Get all the sysPlantLogs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysPlantLog> findAll() {
        log.debug("Request to get all SysPlantLogs");
        return sysPlantLogRepository.findAll();
    }


    /**
     * Get one sysPlantLog by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysPlantLog> findOne(Long id) {
        log.debug("Request to get SysPlantLog : {}", id);
        return sysPlantLogRepository.findById(id);
    }

    /**
     * Delete the sysPlantLog by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysPlantLog : {}", id);
        sysPlantLogRepository.deleteById(id);
    }

    @Override
    public Page<SysPlantLog> findPlantList(String name, Integer pageNum, Integer pageSize) {
        return sysPlantLogRepository.findPlantList(name, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public SysPlantLog findPlantLogById(Long id) {
        return sysPlantLogRepository.findPlantLogById(id);
    }

    @Override
    public ResultObj insertPlantLog(InsertPlantLogVM plantLog) {
        SysPlantLog sysPlantLog = new SysPlantLog();
        sysPlantLog.setName(plantLog.getName());
        sysPlantLog.setImage(plantLog.getImage());
        sysPlantLog.setCreateTime(DateUtil.getZoneDateTime());
        sysPlantLog.setDescription(plantLog.getDescription());
        sysPlantLog.setUpdateTime(DateUtil.getZoneDateTime());
        sysPlantLogRepository.save(sysPlantLog);
        return ResultObj.backInfo(true, 200, "新增成功", null);
    }

    @Override
    public ResultObj updatePlantLog(UpdatePlantLogVM plantLog) {
        if(TypeUtils.isEmpty(plantLog.getId())){
            return ResultObj.backInfo(false, 200, "编辑失败", null);
        }
        SysPlantLog sysPlantLog = new SysPlantLog();
        sysPlantLog.setId(plantLog.getId());
        sysPlantLog.setName(plantLog.getName());
        sysPlantLog.setImage(plantLog.getImage());
        sysPlantLog.setCreateTime(plantLog.getCreateTime());
        sysPlantLog.setDescription(plantLog.getDescription());
        sysPlantLog.setUpdateTime(DateUtil.getZoneDateTime());
        sysPlantLogRepository.save(sysPlantLog);
        return ResultObj.backInfo(true, 200, "编辑成功", null);
    }

    @Override
    public ResultObj deletePlantLog(Long id) {
        if(TypeUtils.isEmpty(id)){
            return ResultObj.backInfo(false, 200, "删除失败", null);
        }
        sysPlantLogRepository.deleteById(id);
        return ResultObj.backInfo(true, 200, "删除成功", null);
    }
}
