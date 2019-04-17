package com.ybb.mall.repository;

import com.ybb.mall.domain.SysMemberSetting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysMemberSetting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysMemberSettingRepository extends JpaRepository<SysMemberSetting, Long> {
}
