package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.dto.order.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysOrder and its DTO SysOrderDTO.
 */
@Mapper(componentModel = "spring", uses = {SysUserMapper.class, SysReceiverAddressMapper.class})
public interface SysOrderMapper extends EntityMapper<OrderDTO, SysOrder> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "receiverAddress.id", target = "receiverAddressId")
    OrderDTO toDto(SysOrder sysOrder);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "receiverAddressId", target = "receiverAddress")
    @Mapping(target = "orderProducts", ignore = false)
    SysOrder toEntity(OrderDTO orderDTO);

    default SysOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysOrder sysOrder = new SysOrder();
        sysOrder.setId(id);
        return sysOrder;
    }
}
