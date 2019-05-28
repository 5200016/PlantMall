package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.wx.WXShoppingCarService;
import com.ybb.mall.web.rest.controller.wx.vm.ShoppingProductVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 购物车管理
 * @Author 黄志成
 * @Date 2019-05-27
 * @Version
 */

@Api(description="微信小程序-购物车管理")
@RestController
@RequestMapping("/mall/wx")
public class ShoppingCarController {
    private final WXShoppingCarService wxShoppingCarService;

    public ShoppingCarController(WXShoppingCarService wxShoppingCarService) {
        this.wxShoppingCarService = wxShoppingCarService;
    }

    /**
     * 根据用户id查询购物车列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据用户id查询购物车列表")
    @GetMapping("/shopping_car")
    @Timed
    public ResultObj selectShoppingCarList(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam Long userId,
                                           @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                           @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return wxShoppingCarService.findShoppingCarList(userId, pageNum, pageSize);
    }

    /**
     * 修改购物车商品数量
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改购物车商品数量")
    @PutMapping("/shopping_car/product")
    @Timed
    public ResultObj updateShoppingCarProduct(@RequestBody ShoppingProductVM ShoppingProduct) throws URISyntaxException {
        return wxShoppingCarService.updateShoppingProductNumber(ShoppingProduct.getType(), ShoppingProduct.getShoppingProductId());
    }

    /**
     * 删除购物车商品
     *
     * @return
     * @throws URISyntaxException
     */
    @DeleteMapping("/shopping_car/product/{id}")
    @Timed
    public ResultObj deleteShoppingCarProduct(@ApiParam(name = "id", value = "购物车商品id", required = true) @PathVariable Long id) throws URISyntaxException {
        return wxShoppingCarService.deleteShoppingCarProduct(id);
    }
}
