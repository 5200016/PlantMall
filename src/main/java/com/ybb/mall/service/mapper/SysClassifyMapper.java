package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.dto.product.classify.ClassifyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysClassify and its DTO SysClassifyDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SysClassifyMapper extends EntityMapper<ClassifyDTO, SysClassify> {


//    @Mapping(target = "products", ignore = true)
    SysClassify toEntity(ClassifyDTO sysClassifyDTO);

    default SysClassify fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysClassify sysClassify = new SysClassify();
        sysClassify.setId(id);
        return sysClassify;
    }
}
