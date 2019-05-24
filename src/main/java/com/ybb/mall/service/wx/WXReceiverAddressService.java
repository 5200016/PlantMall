package com.ybb.mall.service.wx;

import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.ResultObj;

/**
 * 微信小程序-收货地址管理
 */
public interface WXReceiverAddressService {

    /**
     * 根据openid分页查询用户地址
     * @param openid
     * @return
     */
    ResultObj findUserAddressByOpenid(String openid, Integer pageNum, Integer pageSize);

    /**
     * 根据openid及地址id修改默认地址
     * @param updateAddressStatusVM
     * @return
     */
    ResultObj updateAddressByOpenid(UpdateAddressStatusVM updateAddressStatusVM);

    /**
     * 新增用户地址
     * @param insertUserAddressVM
     * @return
     */
    ResultObj insertUserAddress(InsertUserAddressVM insertUserAddressVM);

    /**
     * 根据id查询收货地址
     * @param id
     * @return
     */
    ResultObj findAddressById(Long id);

    /**
     * 修改用户地址
     * @param updateUserAddressVM
     * @return
     */
    ResultObj updateUserAddress(UpdateUserAddressVM updateUserAddressVM);

    /**
     * 删除用户地址
     * @param id
     * @return
     */
    ResultObj deleteUserAddress(Long id);
}
