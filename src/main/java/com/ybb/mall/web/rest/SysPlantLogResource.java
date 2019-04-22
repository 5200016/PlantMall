package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysPlantLog;
import com.ybb.mall.service.SysPlantLogService;
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
 * REST controller for managing SysPlantLog.
 */
@RestController
@RequestMapping("/api")
public class SysPlantLogResource {

    private final Logger log = LoggerFactory.getLogger(SysPlantLogResource.class);

    private static final String ENTITY_NAME = "sysPlantLog";

    private final SysPlantLogService sysPlantLogService;

    public SysPlantLogResource(SysPlantLogService sysPlantLogService) {
        this.sysPlantLogService = sysPlantLogService;
    }

    /**
     * POST  /sys-plant-logs : Create a new sysPlantLog.
     *
     * @param sysPlantLog the sysPlantLog to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysPlantLog, or with status 400 (Bad Request) if the sysPlantLog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-plant-logs")
    @Timed
    public ResponseEntity<SysPlantLog> createSysPlantLog(@RequestBody SysPlantLog sysPlantLog) throws URISyntaxException {
        log.debug("REST request to save SysPlantLog : {}", sysPlantLog);
        if (sysPlantLog.getId() != null) {
            throw new BadRequestAlertException("A new sysPlantLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysPlantLog result = sysPlantLogService.save(sysPlantLog);
        return ResponseEntity.created(new URI("/api/sys-plant-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-plant-logs : Updates an existing sysPlantLog.
     *
     * @param sysPlantLog the sysPlantLog to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysPlantLog,
     * or with status 400 (Bad Request) if the sysPlantLog is not valid,
     * or with status 500 (Internal Server Error) if the sysPlantLog couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-plant-logs")
    @Timed
    public ResponseEntity<SysPlantLog> updateSysPlantLog(@RequestBody SysPlantLog sysPlantLog) throws URISyntaxException {
        log.debug("REST request to update SysPlantLog : {}", sysPlantLog);
        if (sysPlantLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysPlantLog result = sysPlantLogService.save(sysPlantLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysPlantLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-plant-logs : get all the sysPlantLogs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysPlantLogs in body
     */
    @GetMapping("/sys-plant-logs")
    @Timed
    public List<SysPlantLog> getAllSysPlantLogs() {
        log.debug("REST request to get all SysPlantLogs");
        return sysPlantLogService.findAll();
    }

    /**
     * GET  /sys-plant-logs/:id : get the "id" sysPlantLog.
     *
     * @param id the id of the sysPlantLog to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysPlantLog, or with status 404 (Not Found)
     */
    @GetMapping("/sys-plant-logs/{id}")
    @Timed
    public ResponseEntity<SysPlantLog> getSysPlantLog(@PathVariable Long id) {
        log.debug("REST request to get SysPlantLog : {}", id);
        Optional<SysPlantLog> sysPlantLog = sysPlantLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysPlantLog);
    }

    /**
     * DELETE  /sys-plant-logs/:id : delete the "id" sysPlantLog.
     *
     * @param id the id of the sysPlantLog to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-plant-logs/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysPlantLog(@PathVariable Long id) {
        log.debug("REST request to delete SysPlantLog : {}", id);
        sysPlantLogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
