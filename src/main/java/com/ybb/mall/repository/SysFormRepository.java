package com.ybb.mall.repository;

import com.ybb.mall.domain.SysForm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysFormRepository extends JpaRepository<SysForm, Long> {

}
