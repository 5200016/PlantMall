package com.ybb.mall.repository;

import com.ybb.mall.domain.SysAppointment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysAppointment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysAppointmentRepository extends JpaRepository<SysAppointment, Long> {

}
