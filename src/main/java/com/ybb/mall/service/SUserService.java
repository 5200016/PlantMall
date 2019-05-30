package com.ybb.mall.service;

import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.service.dto.user.UserListDTO;
import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.vm.user.UpdateUserVM;
import org.springframework.data.domain.Page;

/**
 * 用户管理
 */
public interface SUserService {

    /**
     * 分页查询用户列表
     * @param phone
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize);

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    ResultObj findUserByOpenid(String openid);

    /**
     * 修改用户信息
     * @param updateUser
     * @return
     */
    ResultObj updateUserInfo(UpdateUserVM updateUser);
}
