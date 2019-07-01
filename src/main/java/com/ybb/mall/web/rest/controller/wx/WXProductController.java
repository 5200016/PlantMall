package com.ybb.mall.web.rest.controller.wx;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.service.ReviewService;
import com.ybb.mall.service.SysPlantLogService;
import com.ybb.mall.service.wx.WXClassifyService;
import com.ybb.mall.service.wx.WXProductService;
import com.ybb.mall.web.rest.controller.wx.vm.order.ProductOperationVM;
import com.ybb.mall.web.rest.util.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

/**
 * @Description : 微信小程序-商品管理
 * @Author 黄志成
 * @Date 2019-05-14
 * @Version
 */

@Api(description = "微信小程序-商品管理")
@RestController
@RequestMapping("/mall/wx")
public class WXProductController {

    private final SysPlantLogService plantLogService;

    private final WXClassifyService wxClassifyService;

    private final WXProductService wxProductService;

    private final ReviewService reviewService;

    public WXProductController(SysPlantLogService plantLogService, WXClassifyService wxClassifyService, WXProductService wxProductService, ReviewService reviewService) {
        this.plantLogService = plantLogService;
        this.wxClassifyService = wxClassifyService;
        this.wxProductService = wxProductService;
        this.reviewService = reviewService;
    }

    /**
     * 查询商品分类
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("查询商品分类列表")
    @GetMapping("/classify/list")
    @Timed
    public ResultObj selectClassifyList() throws URISyntaxException {
        return ResultObj.back(200, wxClassifyService.findWXClassify());
    }

    /**
     * 根据商品分类id查询商品列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据商品分类id查询商品列表")
    @GetMapping("/product/list")
    @Timed
    public ResultObj selectProductList(@ApiParam(name = "classifyId", value = "分类id", required = true) @RequestParam Long classifyId,
                                       @ApiParam(name = "type", value = "分类类型", required = true) @RequestParam Integer type,
                                       @ApiParam(name = "name", value = "商品名称", required = true) @RequestParam String name,
                                       @ApiParam(name = "sortFlag", value = "排序字段（0：热门 1：最新 2：价格升序 3价格降序）", required = true) @RequestParam Integer sortFlag,
                                       @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                       @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return wxProductService.findProductList(classifyId, type, name, sortFlag, pageNum, pageSize);
    }

    /**
     * 根据id查询商品详情
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据id查询商品详情")
    @GetMapping("/product")
    @Timed
    public ResultObj selectProductById(@ApiParam(name = "id", value = "商品id", required = true) @RequestParam Long id) throws URISyntaxException {
        return wxProductService.findWXProductById(id);
    }

    /**
     * 商品数量加减运算
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("商品数量加减运算")
    @PutMapping("/product/operation")
    @Timed
    public ResultObj productOperation(@RequestBody ProductOperationVM productOperation) throws URISyntaxException {
        return wxProductService.shoppingProductOperation(productOperation);
    }

    /**
     * 分页模糊查询植物志列表
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("分页模糊查询植物志列表")
    @GetMapping("/plant_log")
    @Timed
    public ResultObj selectPlantLogList(@ApiParam(name = "name", value = "植物名称", required = true) @RequestParam String name,
                                        @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                        @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return ResultObj.back(200, plantLogService.findPlantList(name, pageNum, pageSize));
    }

    /**
     * 根据id查询植物志
     *
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据id查询植物志")
    @GetMapping("/plant_log/{id}")
    @Timed
    public ResultObj selectPlantLogById(@ApiParam(name = "id", value = "植物id", required = true) @PathVariable Long id) throws URISyntaxException {
        return ResultObj.back(200, plantLogService.findPlantLogById(id));
    }

    /**
     * 根据商品id查询评论列表
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据商品id查询评论列表")
    @GetMapping("/review")
    @Timed
    public ResultObj selectProductReviewList(@ApiParam(name = "id", value = "商品id", required = true) @RequestParam Long id) throws URISyntaxException {
        return reviewService.findProductReviewList(id);
    }

    /**
     * 根据商品id分页查询评论列表
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @ApiOperation("根据商品id分页查询评论列表")
    @GetMapping("/review/page")
    @Timed
    public ResultObj selectProductReviewListPage(@ApiParam(name = "id", value = "商品id", required = true) @RequestParam Long id,
                                                 @ApiParam(name = "pageNum", value = "页码", required = true) @RequestParam Integer pageNum,
                                                 @ApiParam(name = "pageSize", value = "数量", required = true) @RequestParam Integer pageSize) throws URISyntaxException {
        return reviewService.findProductReviewListPage(id, pageNum, pageSize);
    }
}
