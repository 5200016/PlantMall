package com.ybb.mall.service;

import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.service.dto.coupon.BriefListDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.coupon.InsertCouponVM;
import com.ybb.mall.web.rest.vm.coupon.UpdateCouponVM;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 优惠券管理
 */
public interface CouponService {

    /**
     * 分页查询优惠券列表
     * 条件：名称（模糊）， 类型
     * @param name
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<SysCoupon> findCouponList(String name, Integer type, Integer pageNum, Integer pageSize);

    /**
     * 新增优惠券
     * @param couponVM
     * @return
     */
    ResultObj insertCoupon(InsertCouponVM couponVM);

    /**
     * 编辑优惠券
     * @param couponVM
     * @return
     */
    ResultObj updateCoupon(UpdateCouponVM couponVM);

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    ResultObj deleteCoupon(Long id);

    /**
     * 查询某优惠券下商品及分类简略信息
     * @param id
     * @return
     */
    BriefListDTO findCouponProductAndClassify(Long id);
}
