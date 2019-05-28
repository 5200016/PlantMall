package com.ybb.mall.repository;

import com.ybb.mall.domain.SysShoppingProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysShoppingProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysShoppingProductRepository extends JpaRepository<SysShoppingProduct, Long> {

}
