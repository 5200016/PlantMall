package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysMaintenanceFinish;
import com.ybb.mall.service.SysMaintenanceFinishService;
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
 * REST controller for managing SysMaintenanceFinish.
 */
@RestController
@RequestMapping("/api")
public class SysMaintenanceFinishResource {

    private final Logger log = LoggerFactory.getLogger(SysMaintenanceFinishResource.class);

    private static final String ENTITY_NAME = "sysMaintenanceFinish";

    private final SysMaintenanceFinishService sysMaintenanceFinishService;

    public SysMaintenanceFinishResource(SysMaintenanceFinishService sysMaintenanceFinishService) {
        this.sysMaintenanceFinishService = sysMaintenanceFinishService;
    }

    /**
     * POST  /sys-maintenance-finishes : Create a new sysMaintenanceFinish.
     *
     * @param sysMaintenanceFinish the sysMaintenanceFinish to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysMaintenanceFinish, or with status 400 (Bad Request) if the sysMaintenanceFinish has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-maintenance-finishes")
    @Timed
    public ResponseEntity<SysMaintenanceFinish> createSysMaintenanceFinish(@RequestBody SysMaintenanceFinish sysMaintenanceFinish) throws URISyntaxException {
        log.debug("REST request to save SysMaintenanceFinish : {}", sysMaintenanceFinish);
        if (sysMaintenanceFinish.getId() != null) {
            throw new BadRequestAlertException("A new sysMaintenanceFinish cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysMaintenanceFinish result = sysMaintenanceFinishService.save(sysMaintenanceFinish);
        return ResponseEntity.created(new URI("/api/sys-maintenance-finishes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-maintenance-finishes : Updates an existing sysMaintenanceFinish.
     *
     * @param sysMaintenanceFinish the sysMaintenanceFinish to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysMaintenanceFinish,
     * or with status 400 (Bad Request) if the sysMaintenanceFinish is not valid,
     * or with status 500 (Internal Server Error) if the sysMaintenanceFinish couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-maintenance-finishes")
    @Timed
    public ResponseEntity<SysMaintenanceFinish> updateSysMaintenanceFinish(@RequestBody SysMaintenanceFinish sysMaintenanceFinish) throws URISyntaxException {
        log.debug("REST request to update SysMaintenanceFinish : {}", sysMaintenanceFinish);
        if (sysMaintenanceFinish.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysMaintenanceFinish result = sysMaintenanceFinishService.save(sysMaintenanceFinish);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysMaintenanceFinish.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-maintenance-finishes : get all the sysMaintenanceFinishes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysMaintenanceFinishes in body
     */
    @GetMapping("/sys-maintenance-finishes")
    @Timed
    public List<SysMaintenanceFinish> getAllSysMaintenanceFinishes() {
        log.debug("REST request to get all SysMaintenanceFinishes");
        return sysMaintenanceFinishService.findAll();
    }

    /**
     * GET  /sys-maintenance-finishes/:id : get the "id" sysMaintenanceFinish.
     *
     * @param id the id of the sysMaintenanceFinish to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysMaintenanceFinish, or with status 404 (Not Found)
     */
    @GetMapping("/sys-maintenance-finishes/{id}")
    @Timed
    public ResponseEntity<SysMaintenanceFinish> getSysMaintenanceFinish(@PathVariable Long id) {
        log.debug("REST request to get SysMaintenanceFinish : {}", id);
        Optional<SysMaintenanceFinish> sysMaintenanceFinish = sysMaintenanceFinishService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysMaintenanceFinish);
    }

    /**
     * DELETE  /sys-maintenance-finishes/:id : delete the "id" sysMaintenanceFinish.
     *
     * @param id the id of the sysMaintenanceFinish to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-maintenance-finishes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysMaintenanceFinish(@PathVariable Long id) {
        log.debug("REST request to delete SysMaintenanceFinish : {}", id);
        sysMaintenanceFinishService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
