package com.ybb.mall.repository;

import com.ybb.mall.domain.SysUser;
import com.ybb.mall.service.dto.user.UserListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SysUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    @Query(value = "select distinct sys_user from SysUser sys_user left join fetch sys_user.roles",
        countQuery = "select count(distinct sys_user) from SysUser sys_user")
    Page<SysUser> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct sys_user from SysUser sys_user left join fetch sys_user.roles")
    List<SysUser> findAllWithEagerRelationships();

    @Query("select sys_user from SysUser sys_user left join fetch sys_user.roles where sys_user.id =:id")
    Optional<SysUser> findOneWithEagerRelationships(@Param("id") Long id);

    /**
     * 分页模糊查询用户列表（后台管理）
     * 条件：手机号
     */
    @Query("select new com.ybb.mall.service.dto.user.UserListDTO(su.id, su.avatar, su.nickname, su.username, su.sex, su.phone, su.integral, su.growthValue, sml.name) from SysUser su" +
        " left join SysMemberLevel sml on su.memberLevel.id = sml.id" +
        " where su.phone like concat('%', ?1 ,'%')" +
        " order by su.createTime desc")
    Page<UserListDTO> findUserList(String phone, Pageable pageable);
}
