package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysAdmin;
import com.ybb.mall.service.SysAdminService;
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
 * REST controller for managing SysAdmin.
 */
@RestController
@RequestMapping("/api")
public class SysAdminResource {

    private final Logger log = LoggerFactory.getLogger(SysAdminResource.class);

    private static final String ENTITY_NAME = "sysAdmin";

    private final SysAdminService sysAdminService;

    public SysAdminResource(SysAdminService sysAdminService) {
        this.sysAdminService = sysAdminService;
    }

    /**
     * POST  /sys-admins : Create a new sysAdmin.
     *
     * @param sysAdmin the sysAdmin to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysAdmin, or with status 400 (Bad Request) if the sysAdmin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-admins")
    @Timed
    public ResponseEntity<SysAdmin> createSysAdmin(@RequestBody SysAdmin sysAdmin) throws URISyntaxException {
        log.debug("REST request to save SysAdmin : {}", sysAdmin);
        if (sysAdmin.getId() != null) {
            throw new BadRequestAlertException("A new sysAdmin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysAdmin result = sysAdminService.save(sysAdmin);
        return ResponseEntity.created(new URI("/api/sys-admins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-admins : Updates an existing sysAdmin.
     *
     * @param sysAdmin the sysAdmin to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysAdmin,
     * or with status 400 (Bad Request) if the sysAdmin is not valid,
     * or with status 500 (Internal Server Error) if the sysAdmin couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-admins")
    @Timed
    public ResponseEntity<SysAdmin> updateSysAdmin(@RequestBody SysAdmin sysAdmin) throws URISyntaxException {
        log.debug("REST request to update SysAdmin : {}", sysAdmin);
        if (sysAdmin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysAdmin result = sysAdminService.save(sysAdmin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysAdmin.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-admins : get all the sysAdmins.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysAdmins in body
     */
    @GetMapping("/sys-admins")
    @Timed
    public List<SysAdmin> getAllSysAdmins() {
        log.debug("REST request to get all SysAdmins");
        return sysAdminService.findAll();
    }

    /**
     * GET  /sys-admins/:id : get the "id" sysAdmin.
     *
     * @param id the id of the sysAdmin to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysAdmin, or with status 404 (Not Found)
     */
    @GetMapping("/sys-admins/{id}")
    @Timed
    public ResponseEntity<SysAdmin> getSysAdmin(@PathVariable Long id) {
        log.debug("REST request to get SysAdmin : {}", id);
        Optional<SysAdmin> sysAdmin = sysAdminService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysAdmin);
    }

    /**
     * DELETE  /sys-admins/:id : delete the "id" sysAdmin.
     *
     * @param id the id of the sysAdmin to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-admins/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysAdmin(@PathVariable Long id) {
        log.debug("REST request to delete SysAdmin : {}", id);
        sysAdminService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
