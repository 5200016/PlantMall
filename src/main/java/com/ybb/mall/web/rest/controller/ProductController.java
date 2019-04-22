package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.SysClassifyService;
import com.ybb.mall.service.SysPlantLogService;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.classify.UpdateClassifyVM;
import com.ybb.mall.web.rest.vm.product.InsertPlantLogVM;
import com.ybb.mall.web.rest.vm.product.UpdatePlantLogVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 商品管理
 * @Author 黄志成
 * @Date 2019-04-19
 * @Version
 */
@Api(description="商品管理")
@RestController
@RequestMapping("/mall")
public class ProductController {
    private final SysPlantLogService plantLogService;

    private final SysClassifyService classifyService;

    public ProductController(SysPlantLogService plantLogService, SysClassifyService classifyService) {
        this.plantLogService = plantLogService;
        this.classifyService = classifyService;
    }

    /****************************  商品分类  ****************************/

    /**
     * 分页模糊查询商品分类
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页模糊查询商品分类")
    @GetMapping("/classify")
    @Timed
    public ResultObj selectClassifyList(@ApiParam(name="name",value="商品分类名称",required=true) @RequestParam String name,
                                        @ApiParam(name="type",value="商品类型（0：出售， 1：租赁）",required=true) @RequestParam Integer type,
                                        @ApiParam(name="pageNum",value="页码",required=true) @RequestParam Integer pageNum,
                                        @ApiParam(name="pageSize",value="数量",required=true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, classifyService.findClassifyList(name, type, pageNum, pageSize));
    }

    /**
     * 新增商品分类
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增商品分类")
    @PostMapping("/classify")
    @Timed
    public ResultObj insertClassify(@RequestBody InsertClassifyVM classifyVM) throws URISyntaxException {
        return classifyService.insertClassify(classifyVM);
    }

    /**
     * 修改商品分类
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改商品分类")
    @PutMapping("/classify")
    @Timed
    public ResultObj updateClassify(@RequestBody UpdateClassifyVM classifyVM) throws URISyntaxException {
        return classifyService.updateClassify(classifyVM);
    }

    /**
     * 删除商品分类
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除商品分类")
    @DeleteMapping("/classify/{id}")
    @Timed
    public ResultObj deleteClassify(@ApiParam(name="id",value="主键id",required=true) @PathVariable Long id) throws URISyntaxException {
        return classifyService.deleteClassify(id);
    }

    /****************************  植物志  ****************************/

    /**
     * 分页模糊查询植物志列表
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("充值项目列表查询")
    @GetMapping("/plant_log")
    @Timed
    public ResultObj selectPlantLogList(@ApiParam(name="name",value="植物名称",required=true) @RequestParam String name,
                                        @ApiParam(name="pageNum",value="页码",required=true) @RequestParam Integer pageNum,
                                        @ApiParam(name="pageSize",value="数量",required=true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, plantLogService.findPlantList(name, pageNum, pageSize));
    }

    /**
     * 新增植物志
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增植物志")
    @PostMapping("/plant_log")
    @Timed
    public ResultObj insertPlantLog(@RequestBody InsertPlantLogVM plantLog) throws URISyntaxException {
        return plantLogService.insertPlantLog(plantLog);
    }

    /**
     * 修改植物志
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改植物志")
    @PutMapping("/plant_log")
    @Timed
    public ResultObj updatePlantLog(@RequestBody UpdatePlantLogVM plantLog) throws URISyntaxException {
        return plantLogService.updatePlantLog(plantLog);
    }

    /**
     * 删除植物志
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除植物志")
    @DeleteMapping("/plant_log/{id}")
    @Timed
    public ResultObj deletePlantLog(@ApiParam(name="id",value="主键id",required=true) @PathVariable Long id) throws URISyntaxException {
        return plantLogService.deletePlantLog(id);
    }
}
