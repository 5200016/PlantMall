package com.ybb.mall.repository;

import com.ybb.mall.domain.SysUser;
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

}
