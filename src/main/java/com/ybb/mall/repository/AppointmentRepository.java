package com.ybb.mall.repository;

import com.ybb.mall.domain.SysAppointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Description : 预约
 * @Author 黄志成
 * @Date 2019-04-29
 * @Version
 */

@Repository
public interface AppointmentRepository extends JpaRepository<SysAppointment, Long> {

    /**
     * 修改预约状态
     */
    @Modifying
    @Transactional
    @Query("update SysAppointment sa set sa.status = ?2" +
        " where sa.id = ?1")
    void updateAppointmentStatus(Long id, Integer status);

    /**
     * 根据预约状态分页查询预约列表
     */
    Page<SysAppointment> findAppointmentList(Integer status, Pageable pageable);
}
