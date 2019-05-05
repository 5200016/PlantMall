package com.ybb.mall.service.mapper;

import com.ybb.mall.domain.SysAppointment;
import com.ybb.mall.service.dto.sysdto.SysAppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity SysAppointment and its DTO SysAppointmentDTO.
 */
@Mapper(componentModel = "spring", uses = {SysReceiverAddressMapper.class})
public interface SysAppointmentMapper extends EntityMapper<SysAppointmentDTO, SysAppointment> {

    @Mapping(source = "receiverAddress.id", target = "receiverAddressId")
    SysAppointmentDTO toDto(SysAppointment sysAppointment);

    @Mapping(source = "receiverAddressId", target = "receiverAddress")
    SysAppointment toEntity(SysAppointmentDTO sysAppointmentDTO);

    default SysAppointment fromId(Long id) {
        if (id == null) {
            return null;
        }
        SysAppointment sysAppointment = new SysAppointment();
        sysAppointment.setId(id);
        return sysAppointment;
    }
}
