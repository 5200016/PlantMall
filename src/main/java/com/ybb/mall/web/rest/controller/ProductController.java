package com.ybb.mall.web.rest.controller;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.*;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.product.InsertProductVM;
import com.ybb.mall.web.rest.vm.product.UpdateProductVM;
import com.ybb.mall.web.rest.vm.product.classify.InsertClassifyVM;
import com.ybb.mall.web.rest.vm.product.classify.UpdateClassifyVM;
import com.ybb.mall.web.rest.vm.product.plant.InsertPlantLogVM;
import com.ybb.mall.web.rest.vm.product.plant.UpdatePlantLogVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

/**
 * @Description : 商品管理
 * @Author 黄志成
 * @Date 2019-04-19
 * @Version
 */
@Api(description = "商品管理")
@RestController
@RequestMapping("/mall")
public class ProductController {
    private final SysPlantLogService plantLogService;

    private final ClassifyService classifyService;

    private final ProductService productService;

    private final SysReviewService reviewService;

    public ProductController(SysPlantLogService plantLogService, ClassifyService classifyService, ProductService productService, SysReviewService reviewService) {
        this.plantLogService = plantLogService;
        this.classifyService = classifyService;
        this.productService = productService;
        this.reviewService = reviewService;
    }

    /****************************  商品评论  ****************************/

    /**
     * 分页查询商品评论
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询商品评论")
    @GetMapping("/product/review")
    @Timed
    public ResultObj selectProductReviewList(@ApiParam(name = "id", value = "商品id", required = true) @RequestParam Long id,
                                             @ApiParam(name = "nickname", value = "用户昵称", required = true) @RequestParam String nickname,
                                             @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                             @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, reviewService.findByProductId(id, nickname, pageNum, pageSize));
    }

    /**
     * 批量删除商品评论
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("批量删除商品评论")
    @DeleteMapping("/product/review")
    @Timed
    public ResultObj deleteProductReviewBatch(@RequestBody List<Long> id) throws URISyntaxException {
        return reviewService.deleteProductReviewBatch(id);
    }

    /**
     * 根据id删除商品评论
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据id删除商品评论")
    @DeleteMapping("/product/review/{id}")
    @Timed
    public ResultObj deleteProductReview(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return reviewService.deleteProductReview(id);
    }

    /****************************  商品  ****************************/

    /**
     * 分页模糊查询商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页模糊查询商品")
    @GetMapping("/product")
    @Timed
    public ResultObj selectProductList(@ApiParam(name = "name", value = "商品名称", required = true) @RequestParam String name,
                                       @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                       @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, productService.findProductList(name, pageNum, pageSize));
    }

    /**
     * 查询商品列表（简略信息）
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询商品列表（简略信息）")
    @GetMapping("/product/brief")
    @Timed
    public ResultObj selectProductBrief() throws URISyntaxException {
        return ResultObj.back(200, productService.findProductBrief());
    }

    /**
     * 分页查询商品列表（简略信息）
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询商品列表（简略信息）")
    @GetMapping("/product/brief/page")
    @Timed
    public ResultObj selectProductBriefByPageAndName(@ApiParam(name = "name", value = "商品名称", required = true) @RequestParam String name,
                                                     @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                                     @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, productService.findProductBriefByName(name, pageNum, pageSize));
    }

    /**
     * 新增商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("新增商品")
    @PostMapping("/product")
    @Timed
    public ResultObj insertProduct(@RequestBody InsertProductVM productVM) throws URISyntaxException {
        return productService.insertProduct(productVM);
    }

    /**
     * 修改商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("修改商品")
    @PutMapping("/product")
    @Timed
    public ResultObj updateProduct(@RequestBody UpdateProductVM productVM) throws URISyntaxException {
        return productService.updateProduct(productVM);
    }

    /**
     * 删除商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除商品")
    @DeleteMapping("/product/{id}")
    @Timed
    public ResultObj deleteProduct(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return productService.deleteProduct(id);
    }

    /****************************  商品分类  ****************************/

    /**
     * 分页模糊查询商品分类
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页模糊查询商品分类")
    @GetMapping("/classify")
    @Timed
    public ResultObj selectClassifyList(@ApiParam(name = "name", value = "商品分类名称", required = true) @RequestParam String name,
                                        @ApiParam(name = "type", value = "商品类型（0：出售， 1：租赁）", required = true) @RequestParam Integer type,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                        @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, classifyService.findClassifyList(name, type, pageNum, pageSize));
    }

    /**
     * 分级查询商品分类
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分级查询商品分类")
    @GetMapping("/classify/group")
    @Timed
    public ResultObj selectClassifyGroup() throws URISyntaxException {
        return ResultObj.back(200, classifyService.findSysClassifyGroup());
    }

    /**
     * 查询商品分类及其关联商品
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询商品分类及其关联商品")
    @GetMapping("/classify/product")
    @Timed
    public ResultObj selectClassifyAndProduct() throws URISyntaxException {
        return ResultObj.back(200, classifyService.findClassifyAndProduct());
    }

    /**
     * 查询商品分类（级联）
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询商品分类（级联）")
    @GetMapping("/classify/cascade")
    @Timed
    public ResultObj selectClassifyCascade() throws URISyntaxException {
        return ResultObj.back(200, classifyService.findClassifyCascade());
    }

    /**
     * 分页查询商品分类简略信息
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页查询商品分类简略信息")
    @GetMapping("/classify/brief/page")
    @Timed
    public ResultObj selectClassifyBriefPage(@ApiParam(name = "name", value = "商品分类名称", required = true) @RequestParam String name,
                                             @ApiParam(name = "type", value = "商品类型（0：出售， 1：租赁）", required = true) @RequestParam Integer type,
                                             @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                             @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, classifyService.findSellClassifyPage(type, name, pageNum, pageSize));
    }

    /**
     * 新增商品分类
     *
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
     *
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
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除商品分类")
    @DeleteMapping("/classify/{id}")
    @Timed
    public ResultObj deleteClassify(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return classifyService.deleteClassify(id);
    }

    /****************************  植物志  ****************************/

    /**
     * 分页模糊查询植物志列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("充值项目列表查询")
    @GetMapping("/plant_log")
    @Timed
    public ResultObj selectPlantLogList(@ApiParam(name = "name", value = "植物名称", required = true) @RequestParam String name,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                        @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, plantLogService.findPlantList(name, pageNum, pageSize));
    }

    /**
     * 新增植物志
     *
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
     *
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
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("删除植物志")
    @DeleteMapping("/plant_log/{id}")
    @Timed
    public ResultObj deletePlantLog(@ApiParam(name = "id", value = "主键id", required = true) @PathVariable Long id) throws URISyntaxException {
        return plantLogService.deletePlantLog(id);
    }
}
