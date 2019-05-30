package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysForm;
import com.ybb.mall.service.SysFormService;
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
 * REST controller for managing SysForm.
 */
@RestController
@RequestMapping("/api")
public class SysFormResource {

    private final Logger log = LoggerFactory.getLogger(SysFormResource.class);

    private static final String ENTITY_NAME = "sysForm";

    private final SysFormService sysFormService;

    public SysFormResource(SysFormService sysFormService) {
        this.sysFormService = sysFormService;
    }

    /**
     * POST  /sys-forms : Create a new sysForm.
     *
     * @param sysForm the sysForm to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysForm, or with status 400 (Bad Request) if the sysForm has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-forms")
    @Timed
    public ResponseEntity<SysForm> createSysForm(@RequestBody SysForm sysForm) throws URISyntaxException {
        log.debug("REST request to save SysForm : {}", sysForm);
        if (sysForm.getId() != null) {
            throw new BadRequestAlertException("A new sysForm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysForm result = sysFormService.save(sysForm);
        return ResponseEntity.created(new URI("/api/sys-forms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-forms : Updates an existing sysForm.
     *
     * @param sysForm the sysForm to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysForm,
     * or with status 400 (Bad Request) if the sysForm is not valid,
     * or with status 500 (Internal Server Error) if the sysForm couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-forms")
    @Timed
    public ResponseEntity<SysForm> updateSysForm(@RequestBody SysForm sysForm) throws URISyntaxException {
        log.debug("REST request to update SysForm : {}", sysForm);
        if (sysForm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysForm result = sysFormService.save(sysForm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysForm.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-forms : get all the sysForms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysForms in body
     */
    @GetMapping("/sys-forms")
    @Timed
    public List<SysForm> getAllSysForms() {
        log.debug("REST request to get all SysForms");
        return sysFormService.findAll();
    }

    /**
     * GET  /sys-forms/:id : get the "id" sysForm.
     *
     * @param id the id of the sysForm to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysForm, or with status 404 (Not Found)
     */
    @GetMapping("/sys-forms/{id}")
    @Timed
    public ResponseEntity<SysForm> getSysForm(@PathVariable Long id) {
        log.debug("REST request to get SysForm : {}", id);
        Optional<SysForm> sysForm = sysFormService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysForm);
    }

    /**
     * DELETE  /sys-forms/:id : delete the "id" sysForm.
     *
     * @param id the id of the sysForm to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-forms/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysForm(@PathVariable Long id) {
        log.debug("REST request to delete SysForm : {}", id);
        sysFormService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
