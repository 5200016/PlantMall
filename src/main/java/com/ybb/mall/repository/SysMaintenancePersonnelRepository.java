package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMaintenancePersonnel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMaintenancePersonnelRepository extends JpaRepository<SysMaintenancePersonnel, Long> {

}
