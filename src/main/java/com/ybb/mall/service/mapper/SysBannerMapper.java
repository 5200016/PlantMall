package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.service.dto.sysdto.SysBannerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysBanner and its DTO SysBannerDTO.
 */
@Mapper(componentModel = "spring", uses = {SysProductMapper.class, SysClassifyMapper.class})
public interface SysBannerMapper extends EntityMapper<SysBannerDTO, SysBanner> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "classify.id", target = "classifyId")
    SysBannerDTO toDto(SysBanner sysBanner);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "classifyId", target = "classify")
    SysBanner toEntity(SysBannerDTO sysBannerDTO);

    default SysBanner fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysBanner sysBanner = new SysBanner();
        sysBanner.setId(id);
        return sysBanner;
    }
}
