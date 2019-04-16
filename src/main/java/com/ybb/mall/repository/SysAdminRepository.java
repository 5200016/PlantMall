package com.ybb.mall.repository;

import com.ybb.mall.domain.SysAdmin;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysAdmin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysAdminRepository extends JpaRepository<SysAdmin, Long> {

    /**
     * 根据用户名查询管理员信息
     * @param username
     * @return
     */
    SysAdmin findSysAdminByUsername(String username);
}
