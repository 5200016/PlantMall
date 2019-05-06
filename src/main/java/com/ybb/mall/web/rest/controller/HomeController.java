package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.BannerService;
import com.ybb.mall.service.ModuleService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.home.banner.InsertBannerVM;
import com.ybb.mall.web.rest.vm.home.banner.UpdateBannerVM;
import com.ybb.mall.web.rest.vm.home.module.InsertModuleVM;
import com.ybb.mall.web.rest.vm.home.module.UpdateModuleVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 首页管理
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

@Api(description="首页管理")
@RestController
@RequestMapping("/mall")
public class HomeController {

    private final BannerService bannerService;

    private final ModuleService moduleService;

    public HomeController(BannerService bannerService, ModuleService moduleService) {
        this.bannerService = bannerService;
        this.moduleService = moduleService;
    }

    /****************************  模块  ****************************/

    /**
     * 查询模块列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询模块列表")
    @GetMapping("/module")
    @Timed
    public ResultObj selectModuleList() throws URISyntaxException {
        return ResultObj.back(200, moduleService.findHomeModuleList());
    }

    /**
     * 新增模块
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增模块")
    @PostMapping("/module")
    @Timed
    public ResultObj insertModule(@RequestBody InsertModuleVM moduleVM) throws URISyntaxException {
        return moduleService.insertModule(moduleVM);
    }

    /**
     * 修改模块
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改模块")
    @PutMapping("/module")
    @Timed
    public ResultObj updateModule(@RequestBody UpdateModuleVM moduleVM) throws URISyntaxException {
        return moduleService.updateModule(moduleVM);
    }

    /**
     * 删除模块
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除模块")
    @DeleteMapping("/module/{id}")
    @Timed
    public ResultObj deleteModule(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return moduleService.deleteModule(id);
    }

    /****************************  广告  ****************************/

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
     * 新增广告
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增广告")
    @PostMapping("/banner")
    @Timed
    public ResultObj insertBanner(@RequestBody InsertBannerVM bannerVM) throws URISyntaxException {
        return bannerService.insertBanner(bannerVM);
    }

    /**
     * 修改广告
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改广告")
    @PutMapping("/banner")
    @Timed
    public ResultObj updateBanner(@RequestBody UpdateBannerVM bannerVM) throws URISyntaxException {
        return bannerService.updateBanner(bannerVM);
    }

    /**
     * 删除广告
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除广告")
    @DeleteMapping("/banner/{id}")
    @Timed
    public ResultObj deleteBanner(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return bannerService.deleteBanner(id);
    }
}
