package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.CouponService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.coupon.InsertCouponVM;
import com.ybb.mall.web.rest.vm.coupon.UpdateCouponVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


import java.net.URISyntaxException;

/**
 * @Description : 优惠券管理
 * @Author 黄志成
 * @Date 2019-05-07
 * @Version
 */

@Api(description="优惠券管理")
@RestController
@RequestMapping("/mall")
public class CouponController {
    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    /**
     * 分页查询优惠券列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询优惠券列表")
    @GetMapping("/coupon")
    @Timed
    public ResultObj selectCouponList(@ApiParam(name = "name", value = "名称", required = true) @RequestParam String name,
                                      @ApiParam(name = "type", value = "优惠券类型（0：商品优惠券 1：商品通用券 2：租赁优惠券）", required = true) @RequestParam Integer type,
                                      @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                      @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, couponService.findCouponList(name, type, pageNum, pageSize));
    }

    /**
     * 查询某优惠券下商品及分类简略信息
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询某优惠券下商品及分类简略信息")
    @GetMapping("/coupon/product_classify")
    @Timed
    public ResultObj selectCouponClassifyAndProduct(@ApiParam(name = "id", value = "优惠券id", required = true) @RequestParam Long id) throws URISyntaxException {
        return ResultObj.back(200, couponService.findCouponProductAndClassify(id));
    }

    /**
     * 新增优惠券
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增优惠券")
    @PostMapping("/coupon")
    @Timed
    public ResultObj insertCoupon(@RequestBody InsertCouponVM couponVM) throws URISyntaxException {
        return couponService.insertCoupon(couponVM);
    }

    /**
     * 修改优惠券
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改优惠券")
    @PutMapping("/coupon")
    @Timed
    public ResultObj updateCoupon(@RequestBody UpdateCouponVM couponVM) throws URISyntaxException {
        return couponService.updateCoupon(couponVM);
    }

    /**
     * 删除优惠券
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除优惠券")
    @DeleteMapping("/coupon/{id}")
    @Timed
    public ResultObj deleteCoupon(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return couponService.deleteCoupon(id);
    }
}
