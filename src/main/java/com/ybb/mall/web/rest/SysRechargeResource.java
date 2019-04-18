package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysRecharge;
import com.ybb.mall.service.SysRechargeService;
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
 * REST controller for managing SysRecharge.
 */
@RestController
@RequestMapping("/api")
public class SysRechargeResource {

    private final Logger log = LoggerFactory.getLogger(SysRechargeResource.class);

    private static final String ENTITY_NAME = "sysRecharge";

    private final SysRechargeService sysRechargeService;

    public SysRechargeResource(SysRechargeService sysRechargeService) {
        this.sysRechargeService = sysRechargeService;
    }

    /**
     * POST  /sys-recharges : Create a new sysRecharge.
     *
     * @param sysRecharge the sysRecharge to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysRecharge, or with status 400 (Bad Request) if the sysRecharge has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-recharges")
    @Timed
    public ResponseEntity<SysRecharge> createSysRecharge(@RequestBody SysRecharge sysRecharge) throws URISyntaxException {
        log.debug("REST request to save SysRecharge : {}", sysRecharge);
        if (sysRecharge.getId() != null) {
            throw new BadRequestAlertException("A new sysRecharge cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysRecharge result = sysRechargeService.save(sysRecharge);
        return ResponseEntity.created(new URI("/api/sys-recharges/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-recharges : Updates an existing sysRecharge.
     *
     * @param sysRecharge the sysRecharge to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysRecharge,
     * or with status 400 (Bad Request) if the sysRecharge is not valid,
     * or with status 500 (Internal Server Error) if the sysRecharge couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-recharges")
    @Timed
    public ResponseEntity<SysRecharge> updateSysRecharge(@RequestBody SysRecharge sysRecharge) throws URISyntaxException {
        log.debug("REST request to update SysRecharge : {}", sysRecharge);
        if (sysRecharge.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysRecharge result = sysRechargeService.save(sysRecharge);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysRecharge.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-recharges : get all the sysRecharges.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysRecharges in body
     */
    @GetMapping("/sys-recharges")
    @Timed
    public List<SysRecharge> getAllSysRecharges() {
        log.debug("REST request to get all SysRecharges");
        return sysRechargeService.findAll();
    }

    /**
     * GET  /sys-recharges/:id : get the "id" sysRecharge.
     *
     * @param id the id of the sysRecharge to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysRecharge, or with status 404 (Not Found)
     */
    @GetMapping("/sys-recharges/{id}")
    @Timed
    public ResponseEntity<SysRecharge> getSysRecharge(@PathVariable Long id) {
        log.debug("REST request to get SysRecharge : {}", id);
        Optional<SysRecharge> sysRecharge = sysRechargeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysRecharge);
    }

    /**
     * DELETE  /sys-recharges/:id : delete the "id" sysRecharge.
     *
     * @param id the id of the sysRecharge to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-recharges/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysRecharge(@PathVariable Long id) {
        log.debug("REST request to delete SysRecharge : {}", id);
        sysRechargeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
