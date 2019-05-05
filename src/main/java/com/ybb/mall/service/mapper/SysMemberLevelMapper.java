package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysMemberLevel;
import com.ybb.mall.service.dto.sysdto.SysMemberLevelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysMemberLevel and its DTO SysMemberLevelDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysMemberLevelMapper extends EntityMapper<SysMemberLevelDTO, SysMemberLevel> {


    @Mapping(target = "users", ignore = true)
    SysMemberLevel toEntity(SysMemberLevelDTO sysMemberLevelDTO);

    default SysMemberLevel fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysMemberLevel sysMemberLevel = new SysMemberLevel();
        sysMemberLevel.setId(id);
        return sysMemberLevel;
    }
}
