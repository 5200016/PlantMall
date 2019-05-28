package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysShoppingProduct;
import com.ybb.mall.repository.ShoppingCarRepository;
import com.ybb.mall.repository.ShoppingProductRepository;
import com.ybb.mall.service.mapper.SysShoppingCarMapper;
import com.ybb.mall.service.wx.WXShoppingCarService;
import com.ybb.mall.web.rest.util.ResultObj;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description : 购物车管理
 * @Author 黄志成
 * @Date 2019-05-27
 * @Version
 */

@Service
@Transactional
public class WXShoppingCarServiceImpl implements WXShoppingCarService {

    private final ShoppingCarRepository shoppingCarRepository;

    private final ShoppingProductRepository shoppingProductRepository;

    private final SysShoppingCarMapper sysShoppingCarMapper;

    public WXShoppingCarServiceImpl(ShoppingCarRepository shoppingCarRepository, ShoppingProductRepository shoppingProductRepository, SysShoppingCarMapper sysShoppingCarMapper) {
        this.shoppingCarRepository = shoppingCarRepository;
        this.shoppingProductRepository = shoppingProductRepository;
        this.sysShoppingCarMapper = sysShoppingCarMapper;
    }

    @Override
    public ResultObj findShoppingCarList(Long userId, Integer pageNum, Integer pageSize) {
        return ResultObj.back(200, shoppingCarRepository.findShoppingCarList(userId, PageRequest.of(pageNum, pageSize)).map(sysShoppingCarMapper::toDto));
    }

    @Override
    public ResultObj updateShoppingProductNumber(Integer type, Long shoppingProductId) {
        SysShoppingProduct shoppingProduct = shoppingProductRepository.findShoppingProductById(shoppingProductId);
        Integer productNumber = shoppingProduct.getProductNumber();
        switch (type){
            case 0:
                shoppingProduct.setProductNumber(productNumber - 1);
                break;
            case 1:
                shoppingProduct.setProductNumber(productNumber + 1);
                break;
        }
        shoppingProductRepository.save(shoppingProduct);
        return ResultObj.backCRUDSuccess("操作成功");
    }

    @Override
    public ResultObj deleteShoppingCarProduct(Long id) {
        shoppingProductRepository.deleteById(id);
        return ResultObj.backCRUDSuccess("删除成功");
    }
}
