package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCouponProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCouponProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCouponProductRepository extends JpaRepository<SysCouponProduct, Long> {

}
