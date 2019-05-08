package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCouponProduct;
import com.ybb.mall.service.dto.coupon.BriefDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 优惠券->商品
 */
@Repository
public interface CouponProductRepository extends JpaRepository<SysCouponProduct, Long> {
    /**
     * 根据优惠券id查询商品列表简略信息
     */
    @Query("select new com.ybb.mall.service.dto.coupon.BriefDTO(scp.product.id, scp.product.name)" +
        " from SysCouponProduct scp" +
        " where scp.coupon.id = ?1")
    List<BriefDTO> findProductBrief(Long id);
}
