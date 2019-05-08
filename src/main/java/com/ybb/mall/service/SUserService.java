package com.ybb.mall.service;

import com.ybb.mall.service.dto.user.UserListDTO;
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
}
