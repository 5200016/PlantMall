package com.ybb.mall.service.wx;

import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.web.rest.controller.wx.vm.order.ProductOperationVM;
import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description :
 * @Author 黄志成
 * @Date 2019-05-16
 * @Version
 */

public interface WXProductService {
    /**
     * 根据商品分类id查询商品
     */
    ResultObj findProductList(Long classifyId, Integer classifyType, String name, Integer sortFlag, Integer pageNum, Integer pageSize);

    /**
     * 根据id查询商品详情
     */
    ResultObj findWXProductById(Long id);

    /**
     * 商品数量加减运算
     */
    ResultObj shoppingProductOperation(ProductOperationVM productOperation);
}
