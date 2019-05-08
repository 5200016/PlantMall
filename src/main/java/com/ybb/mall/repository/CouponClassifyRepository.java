package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCouponClassify;
import com.ybb.mall.service.dto.coupon.BriefDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 优惠券->商品分类
 */
@Repository
public interface CouponClassifyRepository extends JpaRepository<SysCouponClassify, Long> {
    /**
     * 根据优惠券id查询分类列表简略信息
     */
    @Query("select new com.ybb.mall.service.dto.coupon.BriefDTO(scc.classify.id, scc.classify.name)" +
        " from SysCouponClassify scc" +
        " where scc.coupon.id = ?1")
    List<BriefDTO> findClassifyBrief(Long id);
}
