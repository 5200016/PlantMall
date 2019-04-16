package com.ybb.mall.service;

import com.ybb.mall.domain.SysShoppingCar;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysShoppingCar.
 */
public interface SysShoppingCarService {

    /**
     * Save a sysShoppingCar.
     *
     * @param sysShoppingCar the entity to save
     * @return the persisted entity
     */
    SysShoppingCar save(SysShoppingCar sysShoppingCar);

    /**
     * Get all the sysShoppingCars.
     *
     * @return the list of entities
     */
    List<SysShoppingCar> findAll();


    /**
     * Get the "id" sysShoppingCar.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysShoppingCar> findOne(Long id);

    /**
     * Delete the "id" sysShoppingCar.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
