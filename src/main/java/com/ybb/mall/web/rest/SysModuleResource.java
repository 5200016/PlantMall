package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysModule;
import com.ybb.mall.service.SysModuleService;
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
 * REST controller for managing SysModule.
 */
@RestController
@RequestMapping("/api")
public class SysModuleResource {

    private final Logger log = LoggerFactory.getLogger(SysModuleResource.class);

    private static final String ENTITY_NAME = "sysModule";

    private final SysModuleService sysModuleService;

    public SysModuleResource(SysModuleService sysModuleService) {
        this.sysModuleService = sysModuleService;
    }

    /**
     * POST  /sys-modules : Create a new sysModule.
     *
     * @param sysModule the sysModule to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysModule, or with status 400 (Bad Request) if the sysModule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-modules")
    @Timed
    public ResponseEntity<SysModule> createSysModule(@RequestBody SysModule sysModule) throws URISyntaxException {
        log.debug("REST request to save SysModule : {}", sysModule);
        if (sysModule.getId() != null) {
            throw new BadRequestAlertException("A new sysModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysModule result = sysModuleService.save(sysModule);
        return ResponseEntity.created(new URI("/api/sys-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-modules : Updates an existing sysModule.
     *
     * @param sysModule the sysModule to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysModule,
     * or with status 400 (Bad Request) if the sysModule is not valid,
     * or with status 500 (Internal Server Error) if the sysModule couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-modules")
    @Timed
    public ResponseEntity<SysModule> updateSysModule(@RequestBody SysModule sysModule) throws URISyntaxException {
        log.debug("REST request to update SysModule : {}", sysModule);
        if (sysModule.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysModule result = sysModuleService.save(sysModule);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysModule.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-modules : get all the sysModules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysModules in body
     */
    @GetMapping("/sys-modules")
    @Timed
    public List<SysModule> getAllSysModules() {
        log.debug("REST request to get all SysModules");
        return sysModuleService.findAll();
    }

    /**
     * GET  /sys-modules/:id : get the "id" sysModule.
     *
     * @param id the id of the sysModule to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysModule, or with status 404 (Not Found)
     */
    @GetMapping("/sys-modules/{id}")
    @Timed
    public ResponseEntity<SysModule> getSysModule(@PathVariable Long id) {
        log.debug("REST request to get SysModule : {}", id);
        Optional<SysModule> sysModule = sysModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysModule);
    }

    /**
     * DELETE  /sys-modules/:id : delete the "id" sysModule.
     *
     * @param id the id of the sysModule to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-modules/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysModule(@PathVariable Long id) {
        log.debug("REST request to delete SysModule : {}", id);
        sysModuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
