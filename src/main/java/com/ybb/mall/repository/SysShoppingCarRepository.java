package com.ybb.mall.repository;

import com.ybb.mall.domain.SysShoppingCar;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SysShoppingCar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysShoppingCarRepository extends JpaRepository<SysShoppingCar, Long> {

}
