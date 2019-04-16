package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMemberLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMemberLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMemberLevelRepository extends JpaRepository<SysMemberLevel, Long> {

    /**
     * 查询会员等级列表（根据level正序）
     * @return
     */
    @Query("select sml from SysMemberLevel sml order by sml.level asc")
    SysMemberLevel findMemberLevelList();
}
