package com.ybb.mall.service.wx;

import com.ybb.mall.web.rest.util.ResultObj;

/**
 * @Description : 购物车管理
 * @Author 黄志成
 * @Date 2019-05-27
 * @Version
 */

public interface WXShoppingCarService {

    /**
     * 查询用户购物车列表
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultObj findShoppingCarList(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 加减购物车商品数量
     * @param type
     * @param shoppingProductId
     * @return
     */
    ResultObj updateShoppingProductNumber(Integer type, Long shoppingProductId);

    /**
     * 删除购物车商品
     * @param id
     * @return
     */
    ResultObj deleteShoppingCarProduct(Long id);
}
