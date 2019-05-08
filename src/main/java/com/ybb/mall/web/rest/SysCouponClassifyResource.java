package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCouponClassify;
import com.ybb.mall.service.SysCouponClassifyService;
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
 * REST controller for managing SysCouponClassify.
 */
@RestController
@RequestMapping("/api")
public class SysCouponClassifyResource {

    private final Logger log = LoggerFactory.getLogger(SysCouponClassifyResource.class);

    private static final String ENTITY_NAME = "sysCouponClassify";

    private final SysCouponClassifyService sysCouponClassifyService;

    public SysCouponClassifyResource(SysCouponClassifyService sysCouponClassifyService) {
        this.sysCouponClassifyService = sysCouponClassifyService;
    }

    /**
     * POST  /sys-coupon-classifies : Create a new sysCouponClassify.
     *
     * @param sysCouponClassify the sysCouponClassify to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCouponClassify, or with status 400 (Bad Request) if the sysCouponClassify has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-coupon-classifies")
    @Timed
    public ResponseEntity<SysCouponClassify> createSysCouponClassify(@RequestBody SysCouponClassify sysCouponClassify) throws URISyntaxException {
        log.debug("REST request to save SysCouponClassify : {}", sysCouponClassify);
        if (sysCouponClassify.getId() != null) {
            throw new BadRequestAlertException("A new sysCouponClassify cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCouponClassify result = sysCouponClassifyService.save(sysCouponClassify);
        return ResponseEntity.created(new URI("/api/sys-coupon-classifies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-coupon-classifies : Updates an existing sysCouponClassify.
     *
     * @param sysCouponClassify the sysCouponClassify to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCouponClassify,
     * or with status 400 (Bad Request) if the sysCouponClassify is not valid,
     * or with status 500 (Internal Server Error) if the sysCouponClassify couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-coupon-classifies")
    @Timed
    public ResponseEntity<SysCouponClassify> updateSysCouponClassify(@RequestBody SysCouponClassify sysCouponClassify) throws URISyntaxException {
        log.debug("REST request to update SysCouponClassify : {}", sysCouponClassify);
        if (sysCouponClassify.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCouponClassify result = sysCouponClassifyService.save(sysCouponClassify);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCouponClassify.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-coupon-classifies : get all the sysCouponClassifies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCouponClassifies in body
     */
    @GetMapping("/sys-coupon-classifies")
    @Timed
    public List<SysCouponClassify> getAllSysCouponClassifies() {
        log.debug("REST request to get all SysCouponClassifies");
        return sysCouponClassifyService.findAll();
    }

    /**
     * GET  /sys-coupon-classifies/:id : get the "id" sysCouponClassify.
     *
     * @param id the id of the sysCouponClassify to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCouponClassify, or with status 404 (Not Found)
     */
    @GetMapping("/sys-coupon-classifies/{id}")
    @Timed
    public ResponseEntity<SysCouponClassify> getSysCouponClassify(@PathVariable Long id) {
        log.debug("REST request to get SysCouponClassify : {}", id);
        Optional<SysCouponClassify> sysCouponClassify = sysCouponClassifyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCouponClassify);
    }

    /**
     * DELETE  /sys-coupon-classifies/:id : delete the "id" sysCouponClassify.
     *
     * @param id the id of the sysCouponClassify to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-coupon-classifies/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCouponClassify(@PathVariable Long id) {
        log.debug("REST request to delete SysCouponClassify : {}", id);
        sysCouponClassifyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
