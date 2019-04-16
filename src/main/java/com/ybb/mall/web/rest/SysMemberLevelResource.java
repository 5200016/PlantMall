package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysMemberLevel;
import com.ybb.mall.service.SysMemberLevelService;
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
 * REST controller for managing SysMemberLevel.
 */
@RestController
@RequestMapping("/api")
public class SysMemberLevelResource {

    private final Logger log = LoggerFactory.getLogger(SysMemberLevelResource.class);

    private static final String ENTITY_NAME = "sysMemberLevel";

    private final SysMemberLevelService sysMemberLevelService;

    public SysMemberLevelResource(SysMemberLevelService sysMemberLevelService) {
        this.sysMemberLevelService = sysMemberLevelService;
    }

    /**
     * POST  /sys-member-levels : Create a new sysMemberLevel.
     *
     * @param sysMemberLevel the sysMemberLevel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysMemberLevel, or with status 400 (Bad Request) if the sysMemberLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-member-levels")
    @Timed
    public ResponseEntity<SysMemberLevel> createSysMemberLevel(@RequestBody SysMemberLevel sysMemberLevel) throws URISyntaxException {
        log.debug("REST request to save SysMemberLevel : {}", sysMemberLevel);
        if (sysMemberLevel.getId() != null) {
            throw new BadRequestAlertException("A new sysMemberLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysMemberLevel result = sysMemberLevelService.save(sysMemberLevel);
        return ResponseEntity.created(new URI("/api/sys-member-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-member-levels : Updates an existing sysMemberLevel.
     *
     * @param sysMemberLevel the sysMemberLevel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysMemberLevel,
     * or with status 400 (Bad Request) if the sysMemberLevel is not valid,
     * or with status 500 (Internal Server Error) if the sysMemberLevel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-member-levels")
    @Timed
    public ResponseEntity<SysMemberLevel> updateSysMemberLevel(@RequestBody SysMemberLevel sysMemberLevel) throws URISyntaxException {
        log.debug("REST request to update SysMemberLevel : {}", sysMemberLevel);
        if (sysMemberLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysMemberLevel result = sysMemberLevelService.save(sysMemberLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysMemberLevel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-member-levels : get all the sysMemberLevels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysMemberLevels in body
     */
    @GetMapping("/sys-member-levels")
    @Timed
    public List<SysMemberLevel> getAllSysMemberLevels() {
        log.debug("REST request to get all SysMemberLevels");
        return sysMemberLevelService.findAll();
    }

    /**
     * GET  /sys-member-levels/:id : get the "id" sysMemberLevel.
     *
     * @param id the id of the sysMemberLevel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysMemberLevel, or with status 404 (Not Found)
     */
    @GetMapping("/sys-member-levels/{id}")
    @Timed
    public ResponseEntity<SysMemberLevel> getSysMemberLevel(@PathVariable Long id) {
        log.debug("REST request to get SysMemberLevel : {}", id);
        Optional<SysMemberLevel> sysMemberLevel = sysMemberLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysMemberLevel);
    }

    /**
     * DELETE  /sys-member-levels/:id : delete the "id" sysMemberLevel.
     *
     * @param id the id of the sysMemberLevel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-member-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysMemberLevel(@PathVariable Long id) {
        log.debug("REST request to delete SysMemberLevel : {}", id);
        sysMemberLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
