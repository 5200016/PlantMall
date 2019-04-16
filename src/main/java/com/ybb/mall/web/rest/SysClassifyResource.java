package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysClassify;
import com.ybb.mall.service.SysClassifyService;
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
 * REST controller for managing SysClassify.
 */
@RestController
@RequestMapping("/api")
public class SysClassifyResource {

    private final Logger log = LoggerFactory.getLogger(SysClassifyResource.class);

    private static final String ENTITY_NAME = "sysClassify";

    private final SysClassifyService sysClassifyService;

    public SysClassifyResource(SysClassifyService sysClassifyService) {
        this.sysClassifyService = sysClassifyService;
    }

    /**
     * POST  /sys-classifies : Create a new sysClassify.
     *
     * @param sysClassify the sysClassify to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysClassify, or with status 400 (Bad Request) if the sysClassify has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-classifies")
    @Timed
    public ResponseEntity<SysClassify> createSysClassify(@RequestBody SysClassify sysClassify) throws URISyntaxException {
        log.debug("REST request to save SysClassify : {}", sysClassify);
        if (sysClassify.getId() != null) {
            throw new BadRequestAlertException("A new sysClassify cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysClassify result = sysClassifyService.save(sysClassify);
        return ResponseEntity.created(new URI("/api/sys-classifies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-classifies : Updates an existing sysClassify.
     *
     * @param sysClassify the sysClassify to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysClassify,
     * or with status 400 (Bad Request) if the sysClassify is not valid,
     * or with status 500 (Internal Server Error) if the sysClassify couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-classifies")
    @Timed
    public ResponseEntity<SysClassify> updateSysClassify(@RequestBody SysClassify sysClassify) throws URISyntaxException {
        log.debug("REST request to update SysClassify : {}", sysClassify);
        if (sysClassify.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysClassify result = sysClassifyService.save(sysClassify);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysClassify.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-classifies : get all the sysClassifies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysClassifies in body
     */
    @GetMapping("/sys-classifies")
    @Timed
    public List<SysClassify> getAllSysClassifies() {
        log.debug("REST request to get all SysClassifies");
        return sysClassifyService.findAll();
    }

    /**
     * GET  /sys-classifies/:id : get the "id" sysClassify.
     *
     * @param id the id of the sysClassify to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysClassify, or with status 404 (Not Found)
     */
    @GetMapping("/sys-classifies/{id}")
    @Timed
    public ResponseEntity<SysClassify> getSysClassify(@PathVariable Long id) {
        log.debug("REST request to get SysClassify : {}", id);
        Optional<SysClassify> sysClassify = sysClassifyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysClassify);
    }

    /**
     * DELETE  /sys-classifies/:id : delete the "id" sysClassify.
     *
     * @param id the id of the sysClassify to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-classifies/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysClassify(@PathVariable Long id) {
        log.debug("REST request to delete SysClassify : {}", id);
        sysClassifyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
