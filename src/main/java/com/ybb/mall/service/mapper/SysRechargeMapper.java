package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysRecharge;
import com.ybb.mall.service.dto.sysdto.SysRechargeDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SysRecharge and its DTO SysRechargeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysRechargeMapper extends EntityMapper<SysRechargeDTO, SysRecharge> {



    default SysRecharge fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRecharge sysRecharge = new SysRecharge();
        sysRecharge.setId(id);
        return sysRecharge;
    }
}
