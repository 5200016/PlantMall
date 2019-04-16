package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysMemberSetting;
import com.ybb.mall.service.SysMemberSettingService;
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
 * REST controller for managing SysMemberSetting.
 */
@RestController
@RequestMapping("/api")
public class SysMemberSettingResource {

    private final Logger log = LoggerFactory.getLogger(SysMemberSettingResource.class);

    private static final String ENTITY_NAME = "sysMemberSetting";

    private final SysMemberSettingService sysMemberSettingService;

    public SysMemberSettingResource(SysMemberSettingService sysMemberSettingService) {
        this.sysMemberSettingService = sysMemberSettingService;
    }

    /**
     * POST  /sys-member-settings : Create a new sysMemberSetting.
     *
     * @param sysMemberSetting the sysMemberSetting to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysMemberSetting, or with status 400 (Bad Request) if the sysMemberSetting has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-member-settings")
    @Timed
    public ResponseEntity<SysMemberSetting> createSysMemberSetting(@RequestBody SysMemberSetting sysMemberSetting) throws URISyntaxException {
        log.debug("REST request to save SysMemberSetting : {}", sysMemberSetting);
        if (sysMemberSetting.getId() != null) {
            throw new BadRequestAlertException("A new sysMemberSetting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysMemberSetting result = sysMemberSettingService.save(sysMemberSetting);
        return ResponseEntity.created(new URI("/api/sys-member-settings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-member-settings : Updates an existing sysMemberSetting.
     *
     * @param sysMemberSetting the sysMemberSetting to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysMemberSetting,
     * or with status 400 (Bad Request) if the sysMemberSetting is not valid,
     * or with status 500 (Internal Server Error) if the sysMemberSetting couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-member-settings")
    @Timed
    public ResponseEntity<SysMemberSetting> updateSysMemberSetting(@RequestBody SysMemberSetting sysMemberSetting) throws URISyntaxException {
        log.debug("REST request to update SysMemberSetting : {}", sysMemberSetting);
        if (sysMemberSetting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysMemberSetting result = sysMemberSettingService.save(sysMemberSetting);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysMemberSetting.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-member-settings : get all the sysMemberSettings.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysMemberSettings in body
     */
    @GetMapping("/sys-member-settings")
    @Timed
    public List<SysMemberSetting> getAllSysMemberSettings() {
        log.debug("REST request to get all SysMemberSettings");
        return sysMemberSettingService.findAll();
    }

    /**
     * GET  /sys-member-settings/:id : get the "id" sysMemberSetting.
     *
     * @param id the id of the sysMemberSetting to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysMemberSetting, or with status 404 (Not Found)
     */
    @GetMapping("/sys-member-settings/{id}")
    @Timed
    public ResponseEntity<SysMemberSetting> getSysMemberSetting(@PathVariable Long id) {
        log.debug("REST request to get SysMemberSetting : {}", id);
        Optional<SysMemberSetting> sysMemberSetting = sysMemberSettingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysMemberSetting);
    }

    /**
     * DELETE  /sys-member-settings/:id : delete the "id" sysMemberSetting.
     *
     * @param id the id of the sysMemberSetting to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-member-settings/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysMemberSetting(@PathVariable Long id) {
        log.debug("REST request to delete SysMemberSetting : {}", id);
        sysMemberSettingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
