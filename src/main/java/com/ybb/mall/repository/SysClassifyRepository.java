package com.ybb.mall.repository;

import com.ybb.mall.domain.SysClassify;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysClassify entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysClassifyRepository extends JpaRepository<SysClassify, Long> {

}
