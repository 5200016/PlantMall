package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.dto.order.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * OrderDTO映射
 */
@Mapper(componentModel = "spring", uses = {SysUserMapper.class, SysReceiverAddressMapper.class, SysMaintenancePersonnelMapper.class})
public interface SysOrderMapper extends EntityMapper<OrderDTO, SysOrder> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "receiverAddress.id", target = "receiverAddressId")
    @Mapping(source = "maintenancePersonnel.id", target = "maintenancePersonnelId")
    OrderDTO toDto(SysOrder sysOrder);

    @Mapping(target = "orderProducts", ignore = true)
    @Mapping(source = "userId", target = "user")
    @Mapping(source = "receiverAddressId", target = "receiverAddress")
    @Mapping(source = "maintenancePersonnelId", target = "maintenancePersonnel")
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
