package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.BannerService;
import com.ybb.mall.service.wx.WXModuleService;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序-商品管理
 * @Author 黄志成
 * @Date 2019-05-20
 * @Version
 */

@Api(description = "微信小程序-商品管理")
@RestController
@RequestMapping("/mall/wx")
public class WXHomeController {

    private final BannerService bannerService;

    private final WXModuleService wxModuleService;

    public WXHomeController(BannerService bannerService, WXModuleService wxModuleService) {
        this.bannerService = bannerService;
        this.wxModuleService = wxModuleService;
    }

    /**
     * 根据type查询广告图片列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据type查询广告图片列表")
    @GetMapping("/banner")
    @Timed
    public ResultObj selectBannerList(@ApiParam(name = "type", value = "类型（0：轮播图 1：图文信息）", required = true) @RequestParam Integer type) throws URISyntaxException {
        return ResultObj.back(200, bannerService.findAllByTypeOrderBySortAsc(type));
    }

    /**
     * 查询模块菜单列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询模块菜单列表")
    @GetMapping("/module/menu")
    @Timed
    public ResultObj selectModuleMenuList(@ApiParam(name = "type", value = "类型（0：顶部菜单 1：底部菜单）", required = true) @RequestParam Integer type) throws URISyntaxException {
        return wxModuleService.findModuleListByType(type);
    }

    /**
     * 根据模块类型查询模块信息
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据模块类型查询模块信息")
    @GetMapping("/module/type")
    @Timed
    public ResultObj selectModuleByType(@ApiParam(name = "type", value = "类型（0：商品分类 1：预约服务，2：植物志）", required = true) @RequestParam Integer type) throws URISyntaxException {
        return wxModuleService.findModuleListByModuleType(type);
    }
}
