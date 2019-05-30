package com.ybb.mall.repository;

import com.ybb.mall.domain.SysForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 微信formId存储
 */
@Repository
public interface FormRepository extends JpaRepository<SysForm, Long> {
    @Query("select sf from SysForm  sf where sf.user.id = ?1")
    List<SysForm> findFormIdByUserId(Long userId);
}
