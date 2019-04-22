package com.ybb.mall.repository;

import com.ybb.mall.domain.SysPlantLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysPlantLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysPlantLogRepository extends JpaRepository<SysPlantLog, Long> {

    /**
     * 分页模糊查询植物志列表（根据创建时间倒序）
     * 条件：名称
     */
    @Query("select spl from SysPlantLog spl where" +
        " spl.name like concat('%', ?1, '%')" +
        " order by spl.createTime desc")
    Page<SysPlantLog> findPlantList(String name, Pageable pageable);
}
