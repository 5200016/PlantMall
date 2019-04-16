package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCollection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysCollection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysCollectionRepository extends JpaRepository<SysCollection, Long> {

}
