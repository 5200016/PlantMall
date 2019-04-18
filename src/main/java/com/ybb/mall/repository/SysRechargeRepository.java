package com.ybb.mall.repository;

import com.ybb.mall.domain.SysRecharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the SysRecharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysRechargeRepository extends JpaRepository<SysRecharge, Long> {

    /**
     * 查询充值项目列表（根据充值金额正序）
     */
    @Query("select sr from SysRecharge sr order by sr.price asc")
    List<SysRecharge> findRechargeList();
}
