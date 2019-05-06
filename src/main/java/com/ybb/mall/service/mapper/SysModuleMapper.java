package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysModule;
import com.ybb.mall.service.dto.home.ModuleDTO;
import org.mapstruct.Mapper;

/**
 * 首页模块mapper
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysModuleMapper extends EntityMapper<ModuleDTO, SysModule> {

    ModuleDTO toDto(SysModule sysModule);

    default SysModule fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysModule sysModule = new SysModule();
        sysModule.setId(id);
        return sysModule;
    }
}
