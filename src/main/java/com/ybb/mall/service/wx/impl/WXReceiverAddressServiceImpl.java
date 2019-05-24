package com.ybb.mall.service.wx.impl;

import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.ReceiverAddressRepository;
import com.ybb.mall.service.wx.WXReceiverAddressService;
import com.ybb.mall.web.rest.controller.wx.vm.InsertUserAddressVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateAddressStatusVM;
import com.ybb.mall.web.rest.controller.wx.vm.UpdateUserAddressVM;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description : 微信小程序-收货地址管理
 * @Author 黄志成
 * @Date 2019-05-13
 * @Version
 */

@Service
@Transactional
public class WXReceiverAddressServiceImpl implements WXReceiverAddressService {

    private final ReceiverAddressRepository receiverAddressRepository;

    public WXReceiverAddressServiceImpl(ReceiverAddressRepository receiverAddressRepository) {
        this.receiverAddressRepository = receiverAddressRepository;
    }

    @Override
    public ResultObj findUserAddressByOpenid(String openid, Integer pageNum, Integer pageSize) {
        if(TypeUtils.isEmpty(openid)){
            return ResultObj.backCRUDError("用户openid为空");
        }else {
            return ResultObj.back(200, receiverAddressRepository.findReceiverAddressByOpenid(openid, PageRequest.of(pageNum, pageSize)));
        }
    }

    @Override
    public ResultObj updateAddressByOpenid(UpdateAddressStatusVM updateAddressStatusVM) {
        List<SysReceiverAddress> receiverAddressList = receiverAddressRepository.findAddressByOpenidAndId(updateAddressStatusVM.getOpenid(), updateAddressStatusVM.getId());
        for(SysReceiverAddress data : receiverAddressList){
            if(data.getStatus().equals(1)){
                data.setStatus(0);
            }
            if(data.getId().equals(updateAddressStatusVM.getId())){
                data.setStatus(1);
            }
        }
        receiverAddressRepository.saveAll(receiverAddressList);
        return ResultObj.backCRUDSuccess("设置成功");
    }

    @Override
    public ResultObj insertUserAddress(InsertUserAddressVM insertUserAddressVM) {
        if(TypeUtils.isEmpty(insertUserAddressVM.getUserId())){
            return ResultObj.backCRUDError("用户未登录");
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(insertUserAddressVM.getUserId());

        SysReceiverAddress receiverAddress = new SysReceiverAddress();
        receiverAddress.setUser(sysUser);
        receiverAddress.setStatus(0);
        receiverAddress.setActive(true);
        receiverAddress.setName(insertUserAddressVM.getName());
        receiverAddress.setPhone(insertUserAddressVM.getPhone());
        receiverAddress.setArea(insertUserAddressVM.getArea());
        receiverAddress.setAddress(insertUserAddressVM.getAddress());
        receiverAddress.setCreateTime(DateUtil.getZoneDateTime());
        receiverAddress.setUpdateTime(DateUtil.getZoneDateTime());
        receiverAddressRepository.save(receiverAddress);
        return ResultObj.backCRUDSuccess("保存成功");
    }

    @Override
    public ResultObj findAddressById(Long id) {
        if(TypeUtils.isEmpty(id)){
            ResultObj.backCRUDError("地址索引获取失败");
        }
        return ResultObj.back(200, receiverAddressRepository.findAddressById(id));
    }

    @Override
    public ResultObj updateUserAddress(UpdateUserAddressVM updateUserAddressVM) {
        if(TypeUtils.isEmpty(updateUserAddressVM.getId())){
            ResultObj.backCRUDError("id不能为空");
        }
        SysReceiverAddress data = receiverAddressRepository.findAddressById(updateUserAddressVM.getId());
        data.setName(updateUserAddressVM.getName());
        data.setPhone(updateUserAddressVM.getPhone());
        data.setArea(updateUserAddressVM.getArea());
        data.setAddress(updateUserAddressVM.getAddress());
        data.setUpdateTime(DateUtil.getZoneDateTime());
        receiverAddressRepository.save(data);
        return ResultObj.backCRUDSuccess("编辑成功");
    }

    @Override
    public ResultObj deleteUserAddress(Long id) {
        if(TypeUtils.isEmpty(id)){
            ResultObj.backCRUDError("id不能为空");
        }
        SysReceiverAddress data = receiverAddressRepository.findAddressById(id);
        data.setActive(false);
        data.setUpdateTime(DateUtil.getZoneDateTime());
        receiverAddressRepository.save(data);
        return ResultObj.backCRUDSuccess("删除成功");
    }
}
