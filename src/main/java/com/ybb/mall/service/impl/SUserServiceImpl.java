package com.ybb.mall.service.impl;

import com.ybb.mall.repository.SUserRepository;
import com.ybb.mall.service.SUserService;
import com.ybb.mall.service.dto.user.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理
 */
@Service
@Transactional
public class SUserServiceImpl implements SUserService {

    private final SUserRepository userRepository;

    public SUserServiceImpl(SUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserListDTO> findUserList(String phone, Integer pageNum, Integer pageSize) {
        return userRepository.findUserList(phone, PageRequest.of(pageNum, pageSize));
    }
}
