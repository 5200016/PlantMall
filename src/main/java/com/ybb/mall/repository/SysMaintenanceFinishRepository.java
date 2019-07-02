package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenanceFinish;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMaintenanceFinish entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMaintenanceFinishRepository extends JpaRepository<SysMaintenanceFinish, Long> {

}
