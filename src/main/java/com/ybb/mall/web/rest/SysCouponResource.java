package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCoupon;
import com.ybb.mall.service.SysCouponService;
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
 * REST controller for managing SysCoupon.
 */
@RestController
@RequestMapping("/api")
public class SysCouponResource {

    private final Logger log = LoggerFactory.getLogger(SysCouponResource.class);

    private static final String ENTITY_NAME = "sysCoupon";

    private final SysCouponService sysCouponService;

    public SysCouponResource(SysCouponService sysCouponService) {
        this.sysCouponService = sysCouponService;
    }

    /**
     * POST  /sys-coupons : Create a new sysCoupon.
     *
     * @param sysCoupon the sysCoupon to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCoupon, or with status 400 (Bad Request) if the sysCoupon has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-coupons")
    @Timed
    public ResponseEntity<SysCoupon> createSysCoupon(@RequestBody SysCoupon sysCoupon) throws URISyntaxException {
        log.debug("REST request to save SysCoupon : {}", sysCoupon);
        if (sysCoupon.getId() != null) {
            throw new BadRequestAlertException("A new sysCoupon cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCoupon result = sysCouponService.save(sysCoupon);
        return ResponseEntity.created(new URI("/api/sys-coupons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-coupons : Updates an existing sysCoupon.
     *
     * @param sysCoupon the sysCoupon to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCoupon,
     * or with status 400 (Bad Request) if the sysCoupon is not valid,
     * or with status 500 (Internal Server Error) if the sysCoupon couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-coupons")
    @Timed
    public ResponseEntity<SysCoupon> updateSysCoupon(@RequestBody SysCoupon sysCoupon) throws URISyntaxException {
        log.debug("REST request to update SysCoupon : {}", sysCoupon);
        if (sysCoupon.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCoupon result = sysCouponService.save(sysCoupon);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCoupon.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-coupons : get all the sysCoupons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCoupons in body
     */
    @GetMapping("/sys-coupons")
    @Timed
    public List<SysCoupon> getAllSysCoupons() {
        log.debug("REST request to get all SysCoupons");
        return sysCouponService.findAll();
    }

    /**
     * GET  /sys-coupons/:id : get the "id" sysCoupon.
     *
     * @param id the id of the sysCoupon to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCoupon, or with status 404 (Not Found)
     */
    @GetMapping("/sys-coupons/{id}")
    @Timed
    public ResponseEntity<SysCoupon> getSysCoupon(@PathVariable Long id) {
        log.debug("REST request to get SysCoupon : {}", id);
        Optional<SysCoupon> sysCoupon = sysCouponService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCoupon);
    }

    /**
     * DELETE  /sys-coupons/:id : delete the "id" sysCoupon.
     *
     * @param id the id of the sysCoupon to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-coupons/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCoupon(@PathVariable Long id) {
        log.debug("REST request to delete SysCoupon : {}", id);
        sysCouponService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
