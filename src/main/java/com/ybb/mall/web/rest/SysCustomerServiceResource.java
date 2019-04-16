package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCustomerService;
import com.ybb.mall.service.SysCustomerServiceService;
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
 * REST controller for managing SysCustomerService.
 */
@RestController
@RequestMapping("/api")
public class SysCustomerServiceResource {

    private final Logger log = LoggerFactory.getLogger(SysCustomerServiceResource.class);

    private static final String ENTITY_NAME = "sysCustomerService";

    private final SysCustomerServiceService sysCustomerServiceService;

    public SysCustomerServiceResource(SysCustomerServiceService sysCustomerServiceService) {
        this.sysCustomerServiceService = sysCustomerServiceService;
    }

    /**
     * POST  /sys-customer-services : Create a new sysCustomerService.
     *
     * @param sysCustomerService the sysCustomerService to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCustomerService, or with status 400 (Bad Request) if the sysCustomerService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-customer-services")
    @Timed
    public ResponseEntity<SysCustomerService> createSysCustomerService(@RequestBody SysCustomerService sysCustomerService) throws URISyntaxException {
        log.debug("REST request to save SysCustomerService : {}", sysCustomerService);
        if (sysCustomerService.getId() != null) {
            throw new BadRequestAlertException("A new sysCustomerService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCustomerService result = sysCustomerServiceService.save(sysCustomerService);
        return ResponseEntity.created(new URI("/api/sys-customer-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-customer-services : Updates an existing sysCustomerService.
     *
     * @param sysCustomerService the sysCustomerService to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCustomerService,
     * or with status 400 (Bad Request) if the sysCustomerService is not valid,
     * or with status 500 (Internal Server Error) if the sysCustomerService couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-customer-services")
    @Timed
    public ResponseEntity<SysCustomerService> updateSysCustomerService(@RequestBody SysCustomerService sysCustomerService) throws URISyntaxException {
        log.debug("REST request to update SysCustomerService : {}", sysCustomerService);
        if (sysCustomerService.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCustomerService result = sysCustomerServiceService.save(sysCustomerService);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCustomerService.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-customer-services : get all the sysCustomerServices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCustomerServices in body
     */
    @GetMapping("/sys-customer-services")
    @Timed
    public List<SysCustomerService> getAllSysCustomerServices() {
        log.debug("REST request to get all SysCustomerServices");
        return sysCustomerServiceService.findAll();
    }

    /**
     * GET  /sys-customer-services/:id : get the "id" sysCustomerService.
     *
     * @param id the id of the sysCustomerService to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCustomerService, or with status 404 (Not Found)
     */
    @GetMapping("/sys-customer-services/{id}")
    @Timed
    public ResponseEntity<SysCustomerService> getSysCustomerService(@PathVariable Long id) {
        log.debug("REST request to get SysCustomerService : {}", id);
        Optional<SysCustomerService> sysCustomerService = sysCustomerServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCustomerService);
    }

    /**
     * DELETE  /sys-customer-services/:id : delete the "id" sysCustomerService.
     *
     * @param id the id of the sysCustomerService to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-customer-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCustomerService(@PathVariable Long id) {
        log.debug("REST request to delete SysCustomerService : {}", id);
        sysCustomerServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
