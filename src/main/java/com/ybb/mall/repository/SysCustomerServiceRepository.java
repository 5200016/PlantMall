package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCustomerService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCustomerService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCustomerServiceRepository extends JpaRepository<SysCustomerService, Long> {

}
