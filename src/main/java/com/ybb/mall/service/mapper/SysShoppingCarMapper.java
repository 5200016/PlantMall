package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysShoppingCar;
import com.ybb.mall.service.dto.sysdto.SysShoppingCarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysShoppingCar and its DTO SysShoppingCarDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysShoppingCarMapper extends EntityMapper<SysShoppingCarDTO, SysShoppingCar> {
    SysShoppingCarDTO toDto(SysShoppingCar sysShoppingCar);

    @Mapping(target = "shoppingProducts", ignore = true)

    SysShoppingCar toEntity(SysShoppingCarDTO sysShoppingCarDTO);

    default SysShoppingCar fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysShoppingCar sysShoppingCar = new SysShoppingCar();
        sysShoppingCar.setId(id);
        return sysShoppingCar;
    }
}
