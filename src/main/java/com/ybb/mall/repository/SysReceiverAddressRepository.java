package com.ybb.mall.repository;

import com.ybb.mall.domain.SysReceiverAddress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysReceiverAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysReceiverAddressRepository extends JpaRepository<SysReceiverAddress, Long> {

}
