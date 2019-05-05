package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysAdmin;
import com.ybb.mall.service.dto.sysdto.SysAdminDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SysAdmin and its DTO SysAdminDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysAdminMapper extends EntityMapper<SysAdminDTO, SysAdmin> {



    default SysAdmin fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysAdmin sysAdmin = new SysAdmin();
        sysAdmin.setId(id);
        return sysAdmin;
    }
}
