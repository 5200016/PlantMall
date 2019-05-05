package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysRole;
import com.ybb.mall.service.dto.sysdto.SysRoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysRole and its DTO SysRoleDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysRoleMapper extends EntityMapper<SysRoleDTO, SysRole> {


    @Mapping(target = "users", ignore = true)
    SysRole toEntity(SysRoleDTO sysRoleDTO);

    default SysRole fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        return sysRole;
    }
}
