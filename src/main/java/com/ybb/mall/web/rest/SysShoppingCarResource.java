package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysShoppingCar;
import com.ybb.mall.service.SysShoppingCarService;
import com.ybb.mall.web.rest.errors.BadRequestAlertException;
import com.ybb.mall.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SysShoppingCar.
 */
@RestController
@RequestMapping("/api")
public class SysShoppingCarResource {

    private final Logger log = LoggerFactory.getLogger(SysShoppingCarResource.class);

    private static final String ENTITY_NAME = "sysShoppingCar";

    private final SysShoppingCarService sysShoppingCarService;

    public SysShoppingCarResource(SysShoppingCarService sysShoppingCarService) {
        this.sysShoppingCarService = sysShoppingCarService;
    }

    /**
     * POST  /sys-shopping-cars : Create a new sysShoppingCar.
     *
     * @param sysShoppingCar the sysShoppingCar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysShoppingCar, or with status 400 (Bad Request) if the sysShoppingCar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-shopping-cars")
    @Timed
    public ResponseEntity<SysShoppingCar> createSysShoppingCar(@RequestBody SysShoppingCar sysShoppingCar) throws URISyntaxException {
        log.debug("REST request to save SysShoppingCar : {}", sysShoppingCar);
        if (sysShoppingCar.getId() != null) {
            throw new BadRequestAlertException("A new sysShoppingCar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysShoppingCar result = sysShoppingCarService.save(sysShoppingCar);
        return ResponseEntity.created(new URI("/api/sys-shopping-cars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-shopping-cars : Updates an existing sysShoppingCar.
     *
     * @param sysShoppingCar the sysShoppingCar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysShoppingCar,
     * or with status 400 (Bad Request) if the sysShoppingCar is not valid,
     * or with status 500 (Internal Server Error) if the sysShoppingCar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-shopping-cars")
    @Timed
    public ResponseEntity<SysShoppingCar> updateSysShoppingCar(@RequestBody SysShoppingCar sysShoppingCar) throws URISyntaxException {
        log.debug("REST request to update SysShoppingCar : {}", sysShoppingCar);
        if (sysShoppingCar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysShoppingCar result = sysShoppingCarService.save(sysShoppingCar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysShoppingCar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-shopping-cars : get all the sysShoppingCars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysShoppingCars in body
     */
    @GetMapping("/sys-shopping-cars")
    @Timed
    public List<SysShoppingCar> getAllSysShoppingCars() {
        log.debug("REST request to get all SysShoppingCars");
        return sysShoppingCarService.findAll();
    }

    /**
     * GET  /sys-shopping-cars/:id : get the "id" sysShoppingCar.
     *
     * @param id the id of the sysShoppingCar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysShoppingCar, or with status 404 (Not Found)
     */
    @GetMapping("/sys-shopping-cars/{id}")
    @Timed
    public ResponseEntity<SysShoppingCar> getSysShoppingCar(@PathVariable Long id) {
        log.debug("REST request to get SysShoppingCar : {}", id);
        Optional<SysShoppingCar> sysShoppingCar = sysShoppingCarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysShoppingCar);
    }

    /**
     * DELETE  /sys-shopping-cars/:id : delete the "id" sysShoppingCar.
     *
     * @param id the id of the sysShoppingCar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-shopping-cars/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysShoppingCar(@PathVariable Long id) {
        log.debug("REST request to delete SysShoppingCar : {}", id);
        sysShoppingCarService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
