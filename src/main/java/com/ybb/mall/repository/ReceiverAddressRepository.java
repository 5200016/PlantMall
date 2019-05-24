package com.ybb.mall.repository;

import com.ybb.mall.domain.SysReceiverAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户收货地址
 */
@Repository
public interface ReceiverAddressRepository extends JpaRepository<SysReceiverAddress, Long> {

    /**
     * 根据openid查询用户地址列表
     */
    @Query("select sra from SysReceiverAddress sra" +
        " where sra.user.openid = ?1" +
        " and sra.active = true" +
        " order by sra.status desc")
    Page<SysReceiverAddress> findReceiverAddressByOpenid(String openid, Pageable pageable);

    /**
     * 根据openid查询用户地址列表
     */
    @Query("select sra from SysReceiverAddress sra" +
        " where sra.user.openid = ?1" +
        " and (sra.status = 1 or sra.id = ?2)")
    List<SysReceiverAddress> findAddressByOpenidAndId(String openid, Long id);

    /**
     * 根据id查询收货地址
     */
    @Query("select sra from SysReceiverAddress sra where sra.id = ?1")
    SysReceiverAddress findAddressById(Long id);

}
