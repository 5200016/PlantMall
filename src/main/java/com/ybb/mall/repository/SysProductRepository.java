package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysProductRepository extends JpaRepository<SysProduct, Long> {

}
