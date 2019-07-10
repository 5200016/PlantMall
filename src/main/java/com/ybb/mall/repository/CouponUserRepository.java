package com.ybb.mall.repository;

import com.ybb.mall.domain.SysCouponUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;


/**
 * 优惠券->商品
 */
@Repository
public interface CouponUserRepository extends JpaRepository<SysCouponUser, Long> {

    /**
     * 查询用户的某一优惠券数量
     */
    @Query("select count(scu) from SysCouponUser scu" +
        " where scu.user.id = ?1" +
        " and scu.coupon.id = ?2")
    Long countCouponByUserId(Long userId, Long couponId);

    /**
     * 查询用户未使用的某一优惠券
     */
    @Query("select scu from SysCouponUser scu" +
        " where scu.user.id = ?1" +
        " and scu.coupon.id = ?2" +
        " and scu.useStatus = 0")
    List<SysCouponUser> findCouponInfoByUserId(Long userId, Long couponId);

    /**
     * 根据userId，使用状态分页查询优惠券
     */
    @Query("select scu from SysCouponUser scu" +
        " where scu.user.id = ?1" +
        " and scu.useStatus = ?2" +
        " and scu.coupon.startTime <= ?3" +
        " and scu.coupon.endTime >= ?3" +
        " order by scu.createTime desc")
    Page<SysCouponUser> findCouponOfUserByStatus(Long userId, Integer status, ZonedDateTime time, Pageable pageable);

    /**
     * 根据userId分页查询优惠券
     */
    @Query("select scu from SysCouponUser scu" +
        " where scu.user.id = ?1" +
        " and scu.coupon.endTime <= ?2" +
        " order by scu.createTime desc")
    Page<SysCouponUser> findCouponOfUser(Long userId, ZonedDateTime time, Pageable pageable);

    /**
     * 根据userId分页查询优惠券
     */
    @Query("select scu from SysCouponUser scu" +
        " where scu.user.id = ?1" +
        " and scu.useStatus = 0" +
        " and scu.coupon.startTime <= ?2" +
        " and scu.coupon.endTime >= ?2" +
        " order by scu.createTime desc")
    List<SysCouponUser> findOrderCouponList(Long userId, ZonedDateTime time);
}
