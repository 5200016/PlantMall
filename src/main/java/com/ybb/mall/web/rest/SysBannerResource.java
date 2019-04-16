package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysBanner;
import com.ybb.mall.service.SysBannerService;
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
 * REST controller for managing SysBanner.
 */
@RestController
@RequestMapping("/api")
public class SysBannerResource {

    private final Logger log = LoggerFactory.getLogger(SysBannerResource.class);

    private static final String ENTITY_NAME = "sysBanner";

    private final SysBannerService sysBannerService;

    public SysBannerResource(SysBannerService sysBannerService) {
        this.sysBannerService = sysBannerService;
    }

    /**
     * POST  /sys-banners : Create a new sysBanner.
     *
     * @param sysBanner the sysBanner to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysBanner, or with status 400 (Bad Request) if the sysBanner has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-banners")
    @Timed
    public ResponseEntity<SysBanner> createSysBanner(@RequestBody SysBanner sysBanner) throws URISyntaxException {
        log.debug("REST request to save SysBanner : {}", sysBanner);
        if (sysBanner.getId() != null) {
            throw new BadRequestAlertException("A new sysBanner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysBanner result = sysBannerService.save(sysBanner);
        return ResponseEntity.created(new URI("/api/sys-banners/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-banners : Updates an existing sysBanner.
     *
     * @param sysBanner the sysBanner to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysBanner,
     * or with status 400 (Bad Request) if the sysBanner is not valid,
     * or with status 500 (Internal Server Error) if the sysBanner couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-banners")
    @Timed
    public ResponseEntity<SysBanner> updateSysBanner(@RequestBody SysBanner sysBanner) throws URISyntaxException {
        log.debug("REST request to update SysBanner : {}", sysBanner);
        if (sysBanner.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysBanner result = sysBannerService.save(sysBanner);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysBanner.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-banners : get all the sysBanners.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysBanners in body
     */
    @GetMapping("/sys-banners")
    @Timed
    public List<SysBanner> getAllSysBanners() {
        log.debug("REST request to get all SysBanners");
        return sysBannerService.findAll();
    }

    /**
     * GET  /sys-banners/:id : get the "id" sysBanner.
     *
     * @param id the id of the sysBanner to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysBanner, or with status 404 (Not Found)
     */
    @GetMapping("/sys-banners/{id}")
    @Timed
    public ResponseEntity<SysBanner> getSysBanner(@PathVariable Long id) {
        log.debug("REST request to get SysBanner : {}", id);
        Optional<SysBanner> sysBanner = sysBannerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysBanner);
    }

    /**
     * DELETE  /sys-banners/:id : delete the "id" sysBanner.
     *
     * @param id the id of the sysBanner to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-banners/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysBanner(@PathVariable Long id) {
        log.debug("REST request to delete SysBanner : {}", id);
        sysBannerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
