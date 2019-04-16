package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysUser;
import com.ybb.mall.service.SysUserService;
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
 * REST controller for managing SysUser.
 */
@RestController
@RequestMapping("/api")
public class SysUserResource {

    private final Logger log = LoggerFactory.getLogger(SysUserResource.class);

    private static final String ENTITY_NAME = "sysUser";

    private final SysUserService sysUserService;

    public SysUserResource(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * POST  /sys-users : Create a new sysUser.
     *
     * @param sysUser the sysUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysUser, or with status 400 (Bad Request) if the sysUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-users")
    @Timed
    public ResponseEntity<SysUser> createSysUser(@RequestBody SysUser sysUser) throws URISyntaxException {
        log.debug("REST request to save SysUser : {}", sysUser);
        if (sysUser.getId() != null) {
            throw new BadRequestAlertException("A new sysUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysUser result = sysUserService.save(sysUser);
        return ResponseEntity.created(new URI("/api/sys-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-users : Updates an existing sysUser.
     *
     * @param sysUser the sysUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysUser,
     * or with status 400 (Bad Request) if the sysUser is not valid,
     * or with status 500 (Internal Server Error) if the sysUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-users")
    @Timed
    public ResponseEntity<SysUser> updateSysUser(@RequestBody SysUser sysUser) throws URISyntaxException {
        log.debug("REST request to update SysUser : {}", sysUser);
        if (sysUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysUser result = sysUserService.save(sysUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-users : get all the sysUsers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of sysUsers in body
     */
    @GetMapping("/sys-users")
    @Timed
    public List<SysUser> getAllSysUsers(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all SysUsers");
        return sysUserService.findAll();
    }

    /**
     * GET  /sys-users/:id : get the "id" sysUser.
     *
     * @param id the id of the sysUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysUser, or with status 404 (Not Found)
     */
    @GetMapping("/sys-users/{id}")
    @Timed
    public ResponseEntity<SysUser> getSysUser(@PathVariable Long id) {
        log.debug("REST request to get SysUser : {}", id);
        Optional<SysUser> sysUser = sysUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysUser);
    }

    /**
     * DELETE  /sys-users/:id : delete the "id" sysUser.
     *
     * @param id the id of the sysUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysUser(@PathVariable Long id) {
        log.debug("REST request to delete SysUser : {}", id);
        sysUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
