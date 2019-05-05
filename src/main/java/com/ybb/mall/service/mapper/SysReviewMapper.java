package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysReview;
import com.ybb.mall.service.dto.sysdto.SysReviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysReview and its DTO SysReviewDTO.
 */
@Mapper(componentModel = "spring", uses = {SysProductMapper.class, SysUserMapper.class})
public interface SysReviewMapper extends EntityMapper<SysReviewDTO, SysReview> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    SysReviewDTO toDto(SysReview sysReview);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "userId", target = "user")
    SysReview toEntity(SysReviewDTO sysReviewDTO);

    default SysReview fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysReview sysReview = new SysReview();
        sysReview.setId(id);
        return sysReview;
    }
}
