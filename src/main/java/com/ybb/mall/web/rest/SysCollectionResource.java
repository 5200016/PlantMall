package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCollection;
import com.ybb.mall.service.SysCollectionService;
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
 * REST controller for managing SysCollection.
 */
@RestController
@RequestMapping("/api")
public class SysCollectionResource {

    private final Logger log = LoggerFactory.getLogger(SysCollectionResource.class);

    private static final String ENTITY_NAME = "sysCollection";

    private final SysCollectionService sysCollectionService;

    public SysCollectionResource(SysCollectionService sysCollectionService) {
        this.sysCollectionService = sysCollectionService;
    }

    /**
     * POST  /sys-collections : Create a new sysCollection.
     *
     * @param sysCollection the sysCollection to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCollection, or with status 400 (Bad Request) if the sysCollection has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-collections")
    @Timed
    public ResponseEntity<SysCollection> createSysCollection(@RequestBody SysCollection sysCollection) throws URISyntaxException {
        log.debug("REST request to save SysCollection : {}", sysCollection);
        if (sysCollection.getId() != null) {
            throw new BadRequestAlertException("A new sysCollection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCollection result = sysCollectionService.save(sysCollection);
        return ResponseEntity.created(new URI("/api/sys-collections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-collections : Updates an existing sysCollection.
     *
     * @param sysCollection the sysCollection to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCollection,
     * or with status 400 (Bad Request) if the sysCollection is not valid,
     * or with status 500 (Internal Server Error) if the sysCollection couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-collections")
    @Timed
    public ResponseEntity<SysCollection> updateSysCollection(@RequestBody SysCollection sysCollection) throws URISyntaxException {
        log.debug("REST request to update SysCollection : {}", sysCollection);
        if (sysCollection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCollection result = sysCollectionService.save(sysCollection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCollection.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-collections : get all the sysCollections.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCollections in body
     */
    @GetMapping("/sys-collections")
    @Timed
    public List<SysCollection> getAllSysCollections() {
        log.debug("REST request to get all SysCollections");
        return sysCollectionService.findAll();
    }

    /**
     * GET  /sys-collections/:id : get the "id" sysCollection.
     *
     * @param id the id of the sysCollection to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCollection, or with status 404 (Not Found)
     */
    @GetMapping("/sys-collections/{id}")
    @Timed
    public ResponseEntity<SysCollection> getSysCollection(@PathVariable Long id) {
        log.debug("REST request to get SysCollection : {}", id);
        Optional<SysCollection> sysCollection = sysCollectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCollection);
    }

    /**
     * DELETE  /sys-collections/:id : delete the "id" sysCollection.
     *
     * @param id the id of the sysCollection to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-collections/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCollection(@PathVariable Long id) {
        log.debug("REST request to delete SysCollection : {}", id);
        sysCollectionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
