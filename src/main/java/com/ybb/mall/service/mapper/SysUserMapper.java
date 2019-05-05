package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysUser;
import com.ybb.mall.service.dto.sysdto.SysUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysUser and its DTO SysUserDTO.
 */
@Mapper(componentModel = "spring", uses = {SysMemberLevelMapper.class, SysRoleMapper.class})
public interface SysUserMapper extends EntityMapper<SysUserDTO, SysUser> {

    @Mapping(source = "memberLevel.id", target = "memberLevelId")
    SysUserDTO toDto(SysUser sysUser);

    @Mapping(source = "memberLevelId", target = "memberLevel")
    @Mapping(target = "receiverAddresses", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "collections", ignore = true)
    SysUser toEntity(SysUserDTO sysUserDTO);

    default SysUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        return sysUser;
    }
}
