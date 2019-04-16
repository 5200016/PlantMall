package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysShoppingCarService;
import com.ybb.mall.domain.SysShoppingCar;
import com.ybb.mall.repository.SysShoppingCarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysShoppingCar.
 */
@Service
@Transactional
public class SysShoppingCarServiceImpl implements SysShoppingCarService {

    private final Logger log = LoggerFactory.getLogger(SysShoppingCarServiceImpl.class);

    private final SysShoppingCarRepository sysShoppingCarRepository;

    public SysShoppingCarServiceImpl(SysShoppingCarRepository sysShoppingCarRepository) {
        this.sysShoppingCarRepository = sysShoppingCarRepository;
    }

    /**
     * Save a sysShoppingCar.
     *
     * @param sysShoppingCar the entity to save
     * @return the persisted entity
     */
    @Override
    public SysShoppingCar save(SysShoppingCar sysShoppingCar) {
        log.debug("Request to save SysShoppingCar : {}", sysShoppingCar);
        return sysShoppingCarRepository.save(sysShoppingCar);
    }

    /**
     * Get all the sysShoppingCars.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysShoppingCar> findAll() {
        log.debug("Request to get all SysShoppingCars");
        return sysShoppingCarRepository.findAll();
    }


    /**
     * Get one sysShoppingCar by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysShoppingCar> findOne(Long id) {
        log.debug("Request to get SysShoppingCar : {}", id);
        return sysShoppingCarRepository.findById(id);
    }

    /**
     * Delete the sysShoppingCar by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysShoppingCar : {}", id);
        sysShoppingCarRepository.deleteById(id);
    }
}
