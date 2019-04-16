package com.ybb.mall.repository;

import com.ybb.mall.domain.SysReceiverAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SysReceiverAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysReceiverAddressRepository extends JpaRepository<SysReceiverAddress, Long> {

    @Query(value = "select distinct sys_receiver_address from SysReceiverAddress sys_receiver_address left join fetch sys_receiver_address.products",
        countQuery = "select count(distinct sys_receiver_address) from SysReceiverAddress sys_receiver_address")
    Page<SysReceiverAddress> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct sys_receiver_address from SysReceiverAddress sys_receiver_address left join fetch sys_receiver_address.products")
    List<SysReceiverAddress> findAllWithEagerRelationships();

    @Query("select sys_receiver_address from SysReceiverAddress sys_receiver_address left join fetch sys_receiver_address.products where sys_receiver_address.id =:id")
    Optional<SysReceiverAddress> findOneWithEagerRelationships(@Param("id") Long id);

}
