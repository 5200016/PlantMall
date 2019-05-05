package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysMaintenancePersonnel;
import com.ybb.mall.service.SysMaintenancePersonnelService;
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
 * REST controller for managing SysMaintenancePersonnel.
 */
@RestController
@RequestMapping("/api")
public class SysMaintenancePersonnelResource {

    private final Logger log = LoggerFactory.getLogger(SysMaintenancePersonnelResource.class);

    private static final String ENTITY_NAME = "sysMaintenancePersonnel";

    private final SysMaintenancePersonnelService sysMaintenancePersonnelService;

    public SysMaintenancePersonnelResource(SysMaintenancePersonnelService sysMaintenancePersonnelService) {
        this.sysMaintenancePersonnelService = sysMaintenancePersonnelService;
    }

    /**
     * POST  /sys-maintenance-personnels : Create a new sysMaintenancePersonnel.
     *
     * @param sysMaintenancePersonnel the sysMaintenancePersonnel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysMaintenancePersonnel, or with status 400 (Bad Request) if the sysMaintenancePersonnel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-maintenance-personnels")
    @Timed
    public ResponseEntity<SysMaintenancePersonnel> createSysMaintenancePersonnel(@RequestBody SysMaintenancePersonnel sysMaintenancePersonnel) throws URISyntaxException {
        log.debug("REST request to save SysMaintenancePersonnel : {}", sysMaintenancePersonnel);
        if (sysMaintenancePersonnel.getId() != null) {
            throw new BadRequestAlertException("A new sysMaintenancePersonnel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysMaintenancePersonnel result = sysMaintenancePersonnelService.save(sysMaintenancePersonnel);
        return ResponseEntity.created(new URI("/api/sys-maintenance-personnels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-maintenance-personnels : Updates an existing sysMaintenancePersonnel.
     *
     * @param sysMaintenancePersonnel the sysMaintenancePersonnel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysMaintenancePersonnel,
     * or with status 400 (Bad Request) if the sysMaintenancePersonnel is not valid,
     * or with status 500 (Internal Server Error) if the sysMaintenancePersonnel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-maintenance-personnels")
    @Timed
    public ResponseEntity<SysMaintenancePersonnel> updateSysMaintenancePersonnel(@RequestBody SysMaintenancePersonnel sysMaintenancePersonnel) throws URISyntaxException {
        log.debug("REST request to update SysMaintenancePersonnel : {}", sysMaintenancePersonnel);
        if (sysMaintenancePersonnel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysMaintenancePersonnel result = sysMaintenancePersonnelService.save(sysMaintenancePersonnel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysMaintenancePersonnel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-maintenance-personnels : get all the sysMaintenancePersonnels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysMaintenancePersonnels in body
     */
    @GetMapping("/sys-maintenance-personnels")
    @Timed
    public List<SysMaintenancePersonnel> getAllSysMaintenancePersonnels() {
        log.debug("REST request to get all SysMaintenancePersonnels");
        return sysMaintenancePersonnelService.findAll();
    }

    /**
     * GET  /sys-maintenance-personnels/:id : get the "id" sysMaintenancePersonnel.
     *
     * @param id the id of the sysMaintenancePersonnel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysMaintenancePersonnel, or with status 404 (Not Found)
     */
    @GetMapping("/sys-maintenance-personnels/{id}")
    @Timed
    public ResponseEntity<SysMaintenancePersonnel> getSysMaintenancePersonnel(@PathVariable Long id) {
        log.debug("REST request to get SysMaintenancePersonnel : {}", id);
        Optional<SysMaintenancePersonnel> sysMaintenancePersonnel = sysMaintenancePersonnelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysMaintenancePersonnel);
    }

    /**
     * DELETE  /sys-maintenance-personnels/:id : delete the "id" sysMaintenancePersonnel.
     *
     * @param id the id of the sysMaintenancePersonnel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-maintenance-personnels/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysMaintenancePersonnel(@PathVariable Long id) {
        log.debug("REST request to delete SysMaintenancePersonnel : {}", id);
        sysMaintenancePersonnelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
