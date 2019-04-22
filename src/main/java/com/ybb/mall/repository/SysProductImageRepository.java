package com.ybb.mall.repository;

import com.ybb.mall.domain.SysProductImage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysProductImage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysProductImageRepository extends JpaRepository<SysProductImage, Long> {

}
