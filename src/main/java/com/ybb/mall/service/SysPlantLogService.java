package com.ybb.mall.service;

import com.ybb.mall.domain.SysPlantLog;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.product.plant.InsertPlantLogVM;
import com.ybb.mall.web.rest.vm.product.plant.UpdatePlantLogVM;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysPlantLog.
 */
public interface SysPlantLogService {

    /**
     * Save a sysPlantLog.
     *
     * @param sysPlantLog the entity to save
     * @return the persisted entity
     */
    SysPlantLog save(SysPlantLog sysPlantLog);

    /**
     * Get all the sysPlantLogs.
     *
     * @return the list of entities
     */
    List<SysPlantLog> findAll();


    /**
     * Get the "id" sysPlantLog.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysPlantLog> findOne(Long id);

    /**
     * Delete the "id" sysPlantLog.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * 分页模糊查询植物志列表（根据创建时间倒序）
     * 条件：名称
     */
    Page<SysPlantLog> findPlantList(String name, Integer pageNum, Integer pageSize);

    /**
     * 植物志id
     */
    SysPlantLog findPlantLogById(Long id);

    /**
     * 新增植物志
     */
    ResultObj insertPlantLog(InsertPlantLogVM plantLog);

    /**
     * 修改植物志
     */
    ResultObj updatePlantLog(UpdatePlantLogVM plantLog);

    /**
     * 删除植物志
     */
    ResultObj deletePlantLog(Long id);
}
