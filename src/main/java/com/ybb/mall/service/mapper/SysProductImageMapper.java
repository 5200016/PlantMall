package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.service.dto.sysdto.SysProductImageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysProductImage and its DTO SysProductImageDTO.
 */
@Mapper(componentModel = "spring", uses = {SysProductMapper.class})
public interface SysProductImageMapper extends EntityMapper<SysProductImageDTO, SysProductImage> {

    @Mapping(source = "product.id", target = "productId")
    SysProductImageDTO toDto(SysProductImage sysProductImage);

    @Mapping(source = "productId", target = "product")
    SysProductImage toEntity(SysProductImageDTO sysProductImageDTO);

    default SysProductImage fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysProductImage sysProductImage = new SysProductImage();
        sysProductImage.setId(id);
        return sysProductImage;
    }
}
