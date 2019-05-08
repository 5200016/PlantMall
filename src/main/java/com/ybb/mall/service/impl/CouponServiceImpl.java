package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.domain.SysCouponProduct;
import com.ybb.mall.repository.CouponRepository;
import com.ybb.mall.service.CouponService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.coupon.BriefVM;
import com.ybb.mall.web.rest.vm.coupon.InsertCouponVM;
import com.ybb.mall.web.rest.vm.coupon.UpdateCouponVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券管理
 */
@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public Page<SysCoupon> findCouponList(String name, Integer type, Integer pageNum, Integer pageSize) {
        Integer typeFlag = 0;
        if(!TypeUtils.isEmpty(type) && !type.equals(-1)){
            typeFlag = 1;
        }
        return couponRepository.findCouponList(name, type, typeFlag, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj insertCoupon(InsertCouponVM couponVM) {

        // 优惠券主表入库
        SysCoupon coupon = new SysCoupon();
        coupon.setName(couponVM.getName());
        coupon.setType(couponVM.getType());
        coupon.setValue(couponVM.getValue());
        coupon.setQuantity(couponVM.getQuantity());
        coupon.setLimit(couponVM.getLimit());
        coupon.setStartTime(couponVM.getStartTime());
        coupon.setEndTime(couponVM.getEndTime());
        coupon.setDescription(couponVM.getDescription());
        coupon.setMoneyOff(couponVM.getMoneyOff());
        coupon.setRange(couponVM.getRange());
        SysCoupon sysCoupon = couponRepository.save(coupon);

        // 优惠券商品从表入库
        if(!TypeUtils.isEmpty(couponVM.getProduct())){
            List<SysCouponProduct> couponProducts = new ArrayList<>();
            for(BriefVM data : couponVM.getProduct()){
                SysCouponProduct info = new SysCouponProduct();
                info.setResidue(sysCoupon.getQuantity());
                info.setCoupon(sysCoupon);
            }
        }
        return null;
    }

    @Override
    public ResultObj updateCoupon(UpdateCouponVM couponVM) {
        return null;
    }

    @Override
    public ResultObj deleteCoupon(UpdateCouponVM couponVM) {
        return null;
    }
}
