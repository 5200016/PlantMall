package com.ybb.mall.repository;

import com.ybb.mall.domain.SysAppointment;
import com.ybb.mall.service.dto.appointment.AppointmentDTO;
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
     * 分页查询预约列表
     * 条件：用户姓名、联系方式（模糊查询），订单状态，（预留时间查询）
     */
    @Query("select new com.ybb.mall.service.dto.appointment.AppointmentDTO(sa.id, sa.time, sa.remark, sa.status, sa.createTime, sra.name, sra.phone)" +
        " from SysAppointment sa" +
        " left join SysReceiverAddress sra on sa.receiverAddress.id = sra.id" +
        " where (sra.name like concat('%', ?1, '%') or sra.phone like concat('%', ?1, '%'))" +
        " and (0 = ?3 or sa.status = ?2)" +
        " order by sa.time desc")
    Page<AppointmentDTO> findAppointmentList(String value, Integer status, Integer statusFlag, Pageable pageable);
}
