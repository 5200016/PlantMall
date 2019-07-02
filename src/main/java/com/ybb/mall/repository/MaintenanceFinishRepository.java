package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenanceFinish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMaintenanceFinish entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaintenanceFinishRepository extends JpaRepository<SysMaintenanceFinish, Long> {

}
