package com.ybb.mall.service;

import com.ybb.mall.service.dto.product.ProductDTO;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.product.InsertProductVM;
import com.ybb.mall.web.rest.vm.product.UpdateProductVM;
import org.springframework.data.domain.Page;

/**
 * @Description : 商品
 * @Author 黄志成
 * @Date 2019-04-28
 * @Version
 */

public interface ProductService {

    /**
     * 分页模糊查询商品列表（根据创建时间倒序）
     * 条件：名称
     */
    Page<ProductDTO> findProductList(String name, Integer pageNum, Integer pageSize);

    /**
     * 新增商品
     */
    ResultObj insertProduct(InsertProductVM productVM);

    /**
     * 修改商品
     */
    ResultObj updateProduct(UpdateProductVM productVM);

    /**
     * 删除商品
     */
    ResultObj deleteProduct(Long id);
}
