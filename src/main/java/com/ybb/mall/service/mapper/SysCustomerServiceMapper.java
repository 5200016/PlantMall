package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.service.dto.sysdto.SysCustomerServiceDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SysCustomerService and its DTO SysCustomerServiceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysCustomerServiceMapper extends EntityMapper<SysCustomerServiceDTO, SysCustomerService> {



    default SysCustomerService fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCustomerService sysCustomerService = new SysCustomerService();
        sysCustomerService.setId(id);
        return sysCustomerService;
    }
}
