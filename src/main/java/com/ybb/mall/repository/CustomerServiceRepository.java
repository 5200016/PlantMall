package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 商城客服
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerServiceRepository extends JpaRepository<SysCustomerService, Long> {

}
