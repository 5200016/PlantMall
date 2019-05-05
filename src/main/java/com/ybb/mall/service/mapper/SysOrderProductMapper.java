package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.service.dto.sysdto.SysOrderProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysOrderProduct and its DTO SysOrderProductDTO.
 */
@Mapper(componentModel = "spring", uses = {SysOrderMapper.class, SysProductMapper.class})
public interface SysOrderProductMapper extends EntityMapper<SysOrderProductDTO, SysOrderProduct> {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    SysOrderProductDTO toDto(SysOrderProduct sysOrderProduct);

    @Mapping(source = "orderId", target = "order")
    @Mapping(source = "productId", target = "product")
    SysOrderProduct toEntity(SysOrderProductDTO sysOrderProductDTO);

    default SysOrderProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysOrderProduct sysOrderProduct = new SysOrderProduct();
        sysOrderProduct.setId(id);
        return sysOrderProduct;
    }
}
