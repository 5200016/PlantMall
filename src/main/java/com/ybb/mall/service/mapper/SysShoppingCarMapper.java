package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysShoppingCar;
import com.ybb.mall.service.dto.sysdto.SysShoppingCarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysShoppingCar and its DTO SysShoppingCarDTO.
 */
@Mapper(componentModel = "spring", uses = {SysProductMapper.class})
public interface SysShoppingCarMapper extends EntityMapper<SysShoppingCarDTO, SysShoppingCar> {

    @Mapping(source = "product.id", target = "productId")
    SysShoppingCarDTO toDto(SysShoppingCar sysShoppingCar);

    @Mapping(source = "productId", target = "product")
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
