package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.ReceiverAddressRepository;
import com.ybb.mall.repository.SUserRepository;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.service.dto.user.UserListDTO;
import com.ybb.mall.service.dto.user.WXUserDTO;
import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户管理
 */
@Service
@Transactional
public class SUserServiceImpl implements SUserService {

    private final SUserRepository userRepository;

    private final ReceiverAddressRepository receiverAddressRepository;

    public SUserServiceImpl(SUserRepository userRepository, ReceiverAddressRepository receiverAddressRepository) {
        this.userRepository = userRepository;
        this.receiverAddressRepository = receiverAddressRepository;
    }

    @Override
    public Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize) {
        return userRepository.findUserList(phone, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public ResultObj findUserByOpenid(String openid) {
        WXUserDTO result = userRepository.findUserByOpenid(openid);
        if(TypeUtils.isEmpty(result)){
            return ResultObj.backCRUDError("用户未绑定");
        }
        return ResultObj.back(200, result);
    }
}
