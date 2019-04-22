package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysProductImage;
import com.ybb.mall.service.SysProductImageService;
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
 * REST controller for managing SysProductImage.
 */
@RestController
@RequestMapping("/api")
public class SysProductImageResource {

    private final Logger log = LoggerFactory.getLogger(SysProductImageResource.class);

    private static final String ENTITY_NAME = "sysProductImage";

    private final SysProductImageService sysProductImageService;

    public SysProductImageResource(SysProductImageService sysProductImageService) {
        this.sysProductImageService = sysProductImageService;
    }

    /**
     * POST  /sys-product-images : Create a new sysProductImage.
     *
     * @param sysProductImage the sysProductImage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysProductImage, or with status 400 (Bad Request) if the sysProductImage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-product-images")
    @Timed
    public ResponseEntity<SysProductImage> createSysProductImage(@RequestBody SysProductImage sysProductImage) throws URISyntaxException {
        log.debug("REST request to save SysProductImage : {}", sysProductImage);
        if (sysProductImage.getId() != null) {
            throw new BadRequestAlertException("A new sysProductImage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysProductImage result = sysProductImageService.save(sysProductImage);
        return ResponseEntity.created(new URI("/api/sys-product-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-product-images : Updates an existing sysProductImage.
     *
     * @param sysProductImage the sysProductImage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysProductImage,
     * or with status 400 (Bad Request) if the sysProductImage is not valid,
     * or with status 500 (Internal Server Error) if the sysProductImage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-product-images")
    @Timed
    public ResponseEntity<SysProductImage> updateSysProductImage(@RequestBody SysProductImage sysProductImage) throws URISyntaxException {
        log.debug("REST request to update SysProductImage : {}", sysProductImage);
        if (sysProductImage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysProductImage result = sysProductImageService.save(sysProductImage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysProductImage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-product-images : get all the sysProductImages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysProductImages in body
     */
    @GetMapping("/sys-product-images")
    @Timed
    public List<SysProductImage> getAllSysProductImages() {
        log.debug("REST request to get all SysProductImages");
        return sysProductImageService.findAll();
    }

    /**
     * GET  /sys-product-images/:id : get the "id" sysProductImage.
     *
     * @param id the id of the sysProductImage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysProductImage, or with status 404 (Not Found)
     */
    @GetMapping("/sys-product-images/{id}")
    @Timed
    public ResponseEntity<SysProductImage> getSysProductImage(@PathVariable Long id) {
        log.debug("REST request to get SysProductImage : {}", id);
        Optional<SysProductImage> sysProductImage = sysProductImageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysProductImage);
    }

    /**
     * DELETE  /sys-product-images/:id : delete the "id" sysProductImage.
     *
     * @param id the id of the sysProductImage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-product-images/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysProductImage(@PathVariable Long id) {
        log.debug("REST request to delete SysProductImage : {}", id);
        sysProductImageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
