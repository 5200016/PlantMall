package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.service.dto.sysdto.SysReceiverAddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysReceiverAddress and its DTO SysReceiverAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {SysUserMapper.class})
public interface SysReceiverAddressMapper extends EntityMapper<SysReceiverAddressDTO, SysReceiverAddress> {

    @Mapping(source = "user.id", target = "userId")
    SysReceiverAddressDTO toDto(SysReceiverAddress sysReceiverAddress);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "orders", ignore = true)
    SysReceiverAddress toEntity(SysReceiverAddressDTO sysReceiverAddressDTO);

    default SysReceiverAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysReceiverAddress sysReceiverAddress = new SysReceiverAddress();
        sysReceiverAddress.setId(id);
        return sysReceiverAddress;
    }
}
