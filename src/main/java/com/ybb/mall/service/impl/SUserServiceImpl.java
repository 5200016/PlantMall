package com.ybb.mall.service.impl;

import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.repository.MaintenancePersonnelRepository;
import com.ybb.mall.repository.ReceiverAddressRepository;
import com.ybb.mall.repository.SUserRepository;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.service.dto.user.UserListDTO;
import com.ybb.mall.service.dto.user.WXUserDTO;
import com.ybb.mall.web.rest.util.DateUtil;
import com.ybb.mall.web.rest.util.ResultObj;
import com.ybb.mall.web.rest.util.TypeUtils;
import com.ybb.mall.web.rest.vm.user.UpdateUserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户管理
 */
@Service
@Transactional
public class SUserServiceImpl implements SUserService {

    private final SUserRepository userRepository;

    private final ReceiverAddressRepository receiverAddressRepository;

    private final MaintenancePersonnelRepository maintenancePersonnelRepository;

    public SUserServiceImpl(SUserRepository userRepository, ReceiverAddressRepository receiverAddressRepository, MaintenancePersonnelRepository maintenancePersonnelRepository) {
        this.userRepository = userRepository;
        this.receiverAddressRepository = receiverAddressRepository;
        this.maintenancePersonnelRepository = maintenancePersonnelRepository;
    }

    @Override
    public Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize) {
        return userRepository.findUserList(phone, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public List<UserListDTO> findUserListBrief() {
        return userRepository.findUserListBrief();
    }

    @Override
    public ResultObj findUserByOpenid(String openid) {
        WXUserDTO result = userRepository.findUserByOpenid(openid);
        if(TypeUtils.isEmpty(result)){
            return ResultObj.backCRUDError("用户未绑定");
        }
        return ResultObj.back(200, result);
    }

    @Override
    public ResultObj updateUserInfo(UpdateUserVM updateUser) {
        SysUser user = userRepository.findDatabaseUserById(updateUser.getId());
        user.setUsername(updateUser.getUsername());
        user = userRepository.save(user);

        if(updateUser.getSetStatus().equals(1) && TypeUtils.isEmpty(updateUser.getMaintenancePersonnelId())){
            SysMaintenancePersonnel sysMaintenancePersonnel = new SysMaintenancePersonnel();
            sysMaintenancePersonnel.setStatus(0);
            sysMaintenancePersonnel.setUser(user);
            sysMaintenancePersonnel.setCreateTime(DateUtil.getZoneDateTime());
            sysMaintenancePersonnel.setUpdateTime(DateUtil.getZoneDateTime());
            maintenancePersonnelRepository.save(sysMaintenancePersonnel);
        }
        if(updateUser.getSetStatus().equals(0)){
            if(!TypeUtils.isEmpty(updateUser.getMaintenancePersonnelId())){
                maintenancePersonnelRepository.deleteById(updateUser.getMaintenancePersonnelId());
            }
        }

        return ResultObj.backCRUDSuccess("编辑成功");
    }
}
