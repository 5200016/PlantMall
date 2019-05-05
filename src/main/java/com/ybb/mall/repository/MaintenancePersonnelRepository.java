package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 养护人员
 */
@SuppressWarnings("unused")
@Repository
public interface MaintenancePersonnelRepository extends JpaRepository<SysMaintenancePersonnel, Long> {
}
