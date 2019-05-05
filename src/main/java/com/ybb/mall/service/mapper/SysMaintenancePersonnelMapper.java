package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.service.dto.sysdto.SysMaintenancePersonnelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysMaintenancePersonnel and its DTO SysMaintenancePersonnelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysMaintenancePersonnelMapper extends EntityMapper<SysMaintenancePersonnelDTO, SysMaintenancePersonnel> {


    @Mapping(target = "orders", ignore = true)
    SysMaintenancePersonnel toEntity(SysMaintenancePersonnelDTO sysMaintenancePersonnelDTO);

    default SysMaintenancePersonnel fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysMaintenancePersonnel sysMaintenancePersonnel = new SysMaintenancePersonnel();
        sysMaintenancePersonnel.setId(id);
        return sysMaintenancePersonnel;
    }
}
