package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysCollection;
import com.ybb.mall.service.dto.sysdto.SysCollectionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysCollection and its DTO SysCollectionDTO.
 */
@Mapper(componentModel = "spring", uses = {SysUserMapper.class, SysProductMapper.class})
public interface SysCollectionMapper extends EntityMapper<SysCollectionDTO, SysCollection> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    SysCollectionDTO toDto(SysCollection sysCollection);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "productId", target = "product")
    SysCollection toEntity(SysCollectionDTO sysCollectionDTO);

    default SysCollection fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysCollection sysCollection = new SysCollection();
        sysCollection.setId(id);
        return sysCollection;
    }
}
