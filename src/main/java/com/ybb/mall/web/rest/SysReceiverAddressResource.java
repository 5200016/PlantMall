package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysReceiverAddress;
import com.ybb.mall.service.SysReceiverAddressService;
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
 * REST controller for managing SysReceiverAddress.
 */
@RestController
@RequestMapping("/api")
public class SysReceiverAddressResource {

    private final Logger log = LoggerFactory.getLogger(SysReceiverAddressResource.class);

    private static final String ENTITY_NAME = "sysReceiverAddress";

    private final SysReceiverAddressService sysReceiverAddressService;

    public SysReceiverAddressResource(SysReceiverAddressService sysReceiverAddressService) {
        this.sysReceiverAddressService = sysReceiverAddressService;
    }

    /**
     * POST  /sys-receiver-addresses : Create a new sysReceiverAddress.
     *
     * @param sysReceiverAddress the sysReceiverAddress to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysReceiverAddress, or with status 400 (Bad Request) if the sysReceiverAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-receiver-addresses")
    @Timed
    public ResponseEntity<SysReceiverAddress> createSysReceiverAddress(@RequestBody SysReceiverAddress sysReceiverAddress) throws URISyntaxException {
        log.debug("REST request to save SysReceiverAddress : {}", sysReceiverAddress);
        if (sysReceiverAddress.getId() != null) {
            throw new BadRequestAlertException("A new sysReceiverAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysReceiverAddress result = sysReceiverAddressService.save(sysReceiverAddress);
        return ResponseEntity.created(new URI("/api/sys-receiver-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-receiver-addresses : Updates an existing sysReceiverAddress.
     *
     * @param sysReceiverAddress the sysReceiverAddress to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysReceiverAddress,
     * or with status 400 (Bad Request) if the sysReceiverAddress is not valid,
     * or with status 500 (Internal Server Error) if the sysReceiverAddress couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-receiver-addresses")
    @Timed
    public ResponseEntity<SysReceiverAddress> updateSysReceiverAddress(@RequestBody SysReceiverAddress sysReceiverAddress) throws URISyntaxException {
        log.debug("REST request to update SysReceiverAddress : {}", sysReceiverAddress);
        if (sysReceiverAddress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysReceiverAddress result = sysReceiverAddressService.save(sysReceiverAddress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysReceiverAddress.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-receiver-addresses : get all the sysReceiverAddresses.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of sysReceiverAddresses in body
     */
    @GetMapping("/sys-receiver-addresses")
    @Timed
    public List<SysReceiverAddress> getAllSysReceiverAddresses(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all SysReceiverAddresses");
        return sysReceiverAddressService.findAll();
    }

    /**
     * GET  /sys-receiver-addresses/:id : get the "id" sysReceiverAddress.
     *
     * @param id the id of the sysReceiverAddress to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysReceiverAddress, or with status 404 (Not Found)
     */
    @GetMapping("/sys-receiver-addresses/{id}")
    @Timed
    public ResponseEntity<SysReceiverAddress> getSysReceiverAddress(@PathVariable Long id) {
        log.debug("REST request to get SysReceiverAddress : {}", id);
        Optional<SysReceiverAddress> sysReceiverAddress = sysReceiverAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysReceiverAddress);
    }

    /**
     * DELETE  /sys-receiver-addresses/:id : delete the "id" sysReceiverAddress.
     *
     * @param id the id of the sysReceiverAddress to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-receiver-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysReceiverAddress(@PathVariable Long id) {
        log.debug("REST request to delete SysReceiverAddress : {}", id);
        sysReceiverAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
