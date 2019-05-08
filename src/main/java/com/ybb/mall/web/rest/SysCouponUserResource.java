package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCouponUser;
import com.ybb.mall.service.SysCouponUserService;
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
 * REST controller for managing SysCouponUser.
 */
@RestController
@RequestMapping("/api")
public class SysCouponUserResource {

    private final Logger log = LoggerFactory.getLogger(SysCouponUserResource.class);

    private static final String ENTITY_NAME = "sysCouponUser";

    private final SysCouponUserService sysCouponUserService;

    public SysCouponUserResource(SysCouponUserService sysCouponUserService) {
        this.sysCouponUserService = sysCouponUserService;
    }

    /**
     * POST  /sys-coupon-users : Create a new sysCouponUser.
     *
     * @param sysCouponUser the sysCouponUser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCouponUser, or with status 400 (Bad Request) if the sysCouponUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-coupon-users")
    @Timed
    public ResponseEntity<SysCouponUser> createSysCouponUser(@RequestBody SysCouponUser sysCouponUser) throws URISyntaxException {
        log.debug("REST request to save SysCouponUser : {}", sysCouponUser);
        if (sysCouponUser.getId() != null) {
            throw new BadRequestAlertException("A new sysCouponUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCouponUser result = sysCouponUserService.save(sysCouponUser);
        return ResponseEntity.created(new URI("/api/sys-coupon-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-coupon-users : Updates an existing sysCouponUser.
     *
     * @param sysCouponUser the sysCouponUser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCouponUser,
     * or with status 400 (Bad Request) if the sysCouponUser is not valid,
     * or with status 500 (Internal Server Error) if the sysCouponUser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-coupon-users")
    @Timed
    public ResponseEntity<SysCouponUser> updateSysCouponUser(@RequestBody SysCouponUser sysCouponUser) throws URISyntaxException {
        log.debug("REST request to update SysCouponUser : {}", sysCouponUser);
        if (sysCouponUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCouponUser result = sysCouponUserService.save(sysCouponUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCouponUser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-coupon-users : get all the sysCouponUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCouponUsers in body
     */
    @GetMapping("/sys-coupon-users")
    @Timed
    public List<SysCouponUser> getAllSysCouponUsers() {
        log.debug("REST request to get all SysCouponUsers");
        return sysCouponUserService.findAll();
    }

    /**
     * GET  /sys-coupon-users/:id : get the "id" sysCouponUser.
     *
     * @param id the id of the sysCouponUser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCouponUser, or with status 404 (Not Found)
     */
    @GetMapping("/sys-coupon-users/{id}")
    @Timed
    public ResponseEntity<SysCouponUser> getSysCouponUser(@PathVariable Long id) {
        log.debug("REST request to get SysCouponUser : {}", id);
        Optional<SysCouponUser> sysCouponUser = sysCouponUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCouponUser);
    }

    /**
     * DELETE  /sys-coupon-users/:id : delete the "id" sysCouponUser.
     *
     * @param id the id of the sysCouponUser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-coupon-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCouponUser(@PathVariable Long id) {
        log.debug("REST request to delete SysCouponUser : {}", id);
        sysCouponUserService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
