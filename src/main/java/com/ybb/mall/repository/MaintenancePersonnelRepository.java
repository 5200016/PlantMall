package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 养护人员
 */
@SuppressWarnings("unused")
@Repository
public interface MaintenancePersonnelRepository extends JpaRepository<SysMaintenancePersonnel, Long> {
    /**
     * 根据id查询养护人员信息
     */
    @Query("select smp from SysMaintenancePersonnel smp" +
        " where smp.id = ?1")
    SysMaintenancePersonnel findPersonnelById(Long id);
}
