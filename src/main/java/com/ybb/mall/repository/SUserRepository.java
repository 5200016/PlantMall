package com.ybb.mall.repository;

import com.ybb.mall.domain.SysUser;
import com.ybb.mall.service.dto.user.UserListDTO;
import com.ybb.mall.service.dto.user.WXUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 用户管理
 */
@SuppressWarnings("unused")
@Repository
public interface SUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 分页模糊查询用户列表（后台管理）
     * 条件：手机号
     */
    @Query("select new com.ybb.mall.service.dto.user.UserListDTO(su.id, su.avatar, su.nickname, su.username, su.sex, su.phone, su.integral, su.growthValue, sml.name) from SysUser su" +
        " left join SysMemberLevel sml on su.memberLevel.id = sml.id" +
        " where su.phone like concat('%', ?1 ,'%')" +
        " order by su.createTime desc")
    Page<UserListDTO> findUserList(String phone, Pageable pageable);

    /**
     * 根据openid查询用户信息
     */
    @Query("select new com.ybb.mall.service.dto.user.WXUserDTO(su.id, su.avatar, su.nickname, su.username, su.sex, su.phone, su.integral, su.growthValue, sml.name, su.openid) from SysUser su" +
        " left join SysMemberLevel sml on su.memberLevel.id = sml.id" +
        " where su.openid = ?1")
    WXUserDTO findUserByOpenid(String openid);

    /**
     * 根据openid查询用户表数据
     */
    @Query("select su from SysUser su" +
        " where su.openid = ?1")
    SysUser findDatabaseUserByOpenid(String openid);

    /**
     * 根据openid查询用户表数据
     */
    @Query("select su from SysUser su" +
        " where su.id = ?1")
    SysUser findDatabaseUserById(Long id);
}
