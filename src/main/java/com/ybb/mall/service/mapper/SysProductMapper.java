package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.service.dto.product.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysProduct and its DTO SysProductDTO.
 */
@Mapper(componentModel = "spring", uses = {SysClassifyMapper.class})
public interface SysProductMapper extends EntityMapper<ProductDTO, SysProduct> {


    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "shoppingCars", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "collections", ignore = true)
    SysProduct toEntity(ProductDTO sysProductDTO);

    default SysProduct fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysProduct sysProduct = new SysProduct();
        sysProduct.setId(id);
        return sysProduct;
    }
}
