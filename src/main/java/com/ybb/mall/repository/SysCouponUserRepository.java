package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCouponUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCouponUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCouponUserRepository extends JpaRepository<SysCouponUser, Long> {

}
