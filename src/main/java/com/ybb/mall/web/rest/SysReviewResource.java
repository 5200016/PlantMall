package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysReview;
import com.ybb.mall.service.SysReviewService;
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
 * REST controller for managing SysReview.
 */
@RestController
@RequestMapping("/api")
public class SysReviewResource {

    private final Logger log = LoggerFactory.getLogger(SysReviewResource.class);

    private static final String ENTITY_NAME = "sysReview";

    private final SysReviewService sysReviewService;

    public SysReviewResource(SysReviewService sysReviewService) {
        this.sysReviewService = sysReviewService;
    }

    /**
     * POST  /sys-reviews : Create a new sysReview.
     *
     * @param sysReview the sysReview to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysReview, or with status 400 (Bad Request) if the sysReview has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-reviews")
    @Timed
    public ResponseEntity<SysReview> createSysReview(@RequestBody SysReview sysReview) throws URISyntaxException {
        log.debug("REST request to save SysReview : {}", sysReview);
        if (sysReview.getId() != null) {
            throw new BadRequestAlertException("A new sysReview cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysReview result = sysReviewService.save(sysReview);
        return ResponseEntity.created(new URI("/api/sys-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-reviews : Updates an existing sysReview.
     *
     * @param sysReview the sysReview to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysReview,
     * or with status 400 (Bad Request) if the sysReview is not valid,
     * or with status 500 (Internal Server Error) if the sysReview couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-reviews")
    @Timed
    public ResponseEntity<SysReview> updateSysReview(@RequestBody SysReview sysReview) throws URISyntaxException {
        log.debug("REST request to update SysReview : {}", sysReview);
        if (sysReview.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysReview result = sysReviewService.save(sysReview);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysReview.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-reviews : get all the sysReviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysReviews in body
     */
    @GetMapping("/sys-reviews")
    @Timed
    public List<SysReview> getAllSysReviews() {
        log.debug("REST request to get all SysReviews");
        return sysReviewService.findAll();
    }

    /**
     * GET  /sys-reviews/:id : get the "id" sysReview.
     *
     * @param id the id of the sysReview to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysReview, or with status 404 (Not Found)
     */
    @GetMapping("/sys-reviews/{id}")
    @Timed
    public ResponseEntity<SysReview> getSysReview(@PathVariable Long id) {
        log.debug("REST request to get SysReview : {}", id);
        Optional<SysReview> sysReview = sysReviewService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysReview);
    }

    /**
     * DELETE  /sys-reviews/:id : delete the "id" sysReview.
     *
     * @param id the id of the sysReview to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysReview(@PathVariable Long id) {
        log.debug("REST request to delete SysReview : {}", id);
        sysReviewService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
