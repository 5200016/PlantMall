package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysPlantLog;
import com.ybb.mall.service.dto.sysdto.SysPlantLogDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SysPlantLog and its DTO SysPlantLogDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysPlantLogMapper extends EntityMapper<SysPlantLogDTO, SysPlantLog> {



    default SysPlantLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysPlantLog sysPlantLog = new SysPlantLog();
        sysPlantLog.setId(id);
        return sysPlantLog;
    }
}
