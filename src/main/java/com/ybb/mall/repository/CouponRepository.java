package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;


/**
 * 优惠券
 */
@Repository
public interface CouponRepository extends JpaRepository<SysCoupon, Long> {

    /**
     * 分页查询优惠券列表
     * 条件：名称（模糊）， 类型
     */
    @Query("select sc from SysCoupon sc" +
        " where sc.name like concat('%', ?1, '%')" +
        " and (0 = ?3 or sc.type = ?2)" +
        " order by sc.createTime desc")
    Page<SysCoupon> findCouponList(String name, Integer type, Integer typeFlag, Pageable pageable);

    /**
     * 分页查询优惠券列表
     */
    @Query("select sc from SysCoupon sc" +
        " where sc.endTime > ?1" +
        " order by sc.createTime desc")
    Page<SysCoupon> findCouponListPage(ZonedDateTime time, Pageable pageable);

    /**
     * 根据id查询优惠券详情
     */
    @Query("select sc from SysCoupon sc where sc.id = ?1")
    SysCoupon findCouponById(Long id);
}
