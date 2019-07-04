package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMaintenanceFinish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysMaintenanceFinish entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaintenanceFinishRepository extends JpaRepository<SysMaintenanceFinish, Long> {
    /**
     * 根据订单id查询养护任务记录
     */
    @Query("select smf from SysMaintenanceFinish smf where smf.order.id = ?1")
    List<SysMaintenanceFinish> findMaintenanceFinishByOrderId(Long orderId);

    /**
     * 根据订单id，养护时间查询养护任务记录
     */
    @Query("select smf from SysMaintenanceFinish smf where smf.order.id = ?1 and smf.time = ?2")
    List<SysMaintenanceFinish> findMaintenanceFinishByOrderIdAndTime(Long orderId, String time);
}
