package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.service.dto.sysdto.SysMemberSettingDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity SysMemberSetting and its DTO SysMemberSettingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysMemberSettingMapper extends EntityMapper<SysMemberSettingDTO, SysMemberSetting> {



    default SysMemberSetting fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysMemberSetting sysMemberSetting = new SysMemberSetting();
        sysMemberSetting.setId(id);
        return sysMemberSetting;
    }
}
