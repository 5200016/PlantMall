package com.ybb.mall.repository;

import com.ybb.mall.domain.SysOrderProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysOrderProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysOrderProductRepository extends JpaRepository<SysOrderProduct, Long> {

}
