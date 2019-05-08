package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCoupon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCoupon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCouponRepository extends JpaRepository<SysCoupon, Long> {

}
