package com.ybb.mall.repository;

import com.ybb.mall.domain.SysOrder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysOrderRepository extends JpaRepository<SysOrder, Long> {

}
