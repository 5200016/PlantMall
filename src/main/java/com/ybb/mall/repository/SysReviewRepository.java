package com.ybb.mall.repository;

import com.ybb.mall.domain.SysReview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysReview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysReviewRepository extends JpaRepository<SysReview, Long> {

}
