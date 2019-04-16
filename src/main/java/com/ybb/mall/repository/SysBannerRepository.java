package com.ybb.mall.repository;

import com.ybb.mall.domain.SysBanner;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysBanner entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysBannerRepository extends JpaRepository<SysBanner, Long> {

}
