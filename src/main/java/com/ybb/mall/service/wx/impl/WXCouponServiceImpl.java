package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.domain.SysCouponUser;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.CouponRepository;
import com.ybb.mall.repository.CouponUserRepository;
import com.ybb.mall.service.wx.WXCouponService;
import com.ybb.mall.web.rest.controller.wx.vm.coupon.GetCouponVM;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

/**
 * @Description : 微信小程序-优惠券管理
 * @Author 黄志成
 * @Date 2019-05-21
 * @Version
 */

@Service
@Transactional
public class WXCouponServiceImpl implements WXCouponService {

    private final CouponRepository couponRepository;

    private final CouponUserRepository couponUserRepository;

    public WXCouponServiceImpl(CouponRepository couponRepository, CouponUserRepository couponUserRepository) {
        this.couponRepository = couponRepository;
        this.couponUserRepository = couponUserRepository;
    }

    @Override
    public ResultObj findCouponList(Integer pageNum, Integer pageSize) {
        return ResultObj.back(200, couponRepository.findCouponListPage(DateUtil.getZoneDateTime(), PageRequest.of(pageNum, pageSize)));
    }

    @Override
    public ResultObj insertUserOfCoupon(GetCouponVM couponVM) {
        // 判断优惠券剩余、限领、时间
        SysCoupon coupon = couponRepository.findCouponById(couponVM.getCouponId());
        if(!TypeUtils.isEmpty(coupon)){
            // 数量
            Integer quantity = coupon.getQuantity();
            // 已领
            Integer getNumber = coupon.getGetNumber();
            // 限领
            Integer limit = coupon.getLimit();

            // 判断时间
            ZonedDateTime now = DateUtil.getZoneDateTime();
            ZonedDateTime start = coupon.getStartTime();

            Long nowTime = DateUtil.getDate(DateUtil.zonedDateTimeFormat(now, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss").getTime();
            Long startTime = DateUtil.getDate(DateUtil.zonedDateTimeFormat(start, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss").getTime();

            if(startTime > nowTime){
                return ResultObj.backCRUDError("未到领取时间");
            }

            // 剩余优惠券
            Integer residue = quantity - getNumber;
            if(residue <= 0){
                return ResultObj.backCRUDError("已被抢完");
            }

            // 用户拥有当前优惠券数量
            Long number = couponUserRepository.countCouponByUserId(couponVM.getUserId(), couponVM.getCouponId());
            if(number >= limit){
                return ResultObj.backCRUDError("领取超过限制");
            }

            coupon.setGetNumber(getNumber + 1);
            SysCoupon sysCoupon = couponRepository.save(coupon);

            SysCouponUser couponUser = new SysCouponUser();
            couponUser.setUseStatus(0);
            couponUser.setUpdateTime(DateUtil.getZoneDateTime());
            couponUser.setCreateTime(DateUtil.getZoneDateTime());

            SysUser user = new SysUser();
            user.setId(couponVM.getUserId());
            couponUser.setUser(user);
            couponUser.setCoupon(sysCoupon);

            couponUserRepository.save(couponUser);
            return ResultObj.backCRUDSuccess("领取成功");
        }else {
            return ResultObj.backCRUDError("领取失败");
        }
    }

    @Override
    public ResultObj findMyCouponList(Long userId, Integer status, Integer pageNum, Integer pageSize) {
        switch (status){
            case 0:
                Page<SysCouponUser> noUse = couponUserRepository.findCouponOfUserByStatus(userId, status, DateUtil.getZoneDateTime(), PageRequest.of(pageNum, pageSize));
                return ResultObj.back(200, noUse);
            case 1:
                Page<SysCouponUser> use = couponUserRepository.findCouponOfUserByStatus(userId, status, DateUtil.getZoneDateTime(), PageRequest.of(pageNum, pageSize));
                return ResultObj.back(200, use);
            case 2:
                Page<SysCouponUser> past = couponUserRepository.findCouponOfUser(userId, DateUtil.getZoneDateTime(), PageRequest.of(pageNum, pageSize));
                return ResultObj.back(200, past);
        }
        return ResultObj.backCRUDError("暂无数据");
    }

    @Override
    public ResultObj findOrderCouponList(Long userId) {
        return ResultObj.back(200, couponUserRepository.findOrderCouponList(userId, DateUtil.getZoneDateTime()));
    }
}
