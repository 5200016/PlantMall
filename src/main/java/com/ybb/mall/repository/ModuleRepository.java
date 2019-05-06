package com.ybb.mall.repository;

import com.ybb.mall.domain.SysModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 首页模块
 */
@Repository
public interface ModuleRepository extends JpaRepository<SysModule, Long> {

    @Query("select sm from SysModule sm order by sm.sort asc")
    List<SysModule> findHomeModuleList();
}
