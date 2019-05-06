package com.ybb.mall.repository;

import com.ybb.mall.domain.SysModule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysModuleRepository extends JpaRepository<SysModule, Long> {

}
