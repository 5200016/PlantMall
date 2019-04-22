package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysProduct;
import com.ybb.mall.service.SysProductService;
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
 * REST controller for managing SysProduct.
 */
@RestController
@RequestMapping("/api")
public class SysProductResource {

    private final Logger log = LoggerFactory.getLogger(SysProductResource.class);

    private static final String ENTITY_NAME = "sysProduct";

    private final SysProductService sysProductService;

    public SysProductResource(SysProductService sysProductService) {
        this.sysProductService = sysProductService;
    }

    /**
     * POST  /sys-products : Create a new sysProduct.
     *
     * @param sysProduct the sysProduct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysProduct, or with status 400 (Bad Request) if the sysProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-products")
    @Timed
    public ResponseEntity<SysProduct> createSysProduct(@RequestBody SysProduct sysProduct) throws URISyntaxException {
        log.debug("REST request to save SysProduct : {}", sysProduct);
        if (sysProduct.getId() != null) {
            throw new BadRequestAlertException("A new sysProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysProduct result = sysProductService.save(sysProduct);
        return ResponseEntity.created(new URI("/api/sys-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-products : Updates an existing sysProduct.
     *
     * @param sysProduct the sysProduct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysProduct,
     * or with status 400 (Bad Request) if the sysProduct is not valid,
     * or with status 500 (Internal Server Error) if the sysProduct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-products")
    @Timed
    public ResponseEntity<SysProduct> updateSysProduct(@RequestBody SysProduct sysProduct) throws URISyntaxException {
        log.debug("REST request to update SysProduct : {}", sysProduct);
        if (sysProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysProduct result = sysProductService.save(sysProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysProduct.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-products : get all the sysProducts.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of sysProducts in body
     */
    @GetMapping("/sys-products")
    @Timed
    public List<SysProduct> getAllSysProducts(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all SysProducts");
        return sysProductService.findAll();
    }

    /**
     * GET  /sys-products/:id : get the "id" sysProduct.
     *
     * @param id the id of the sysProduct to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysProduct, or with status 404 (Not Found)
     */
    @GetMapping("/sys-products/{id}")
    @Timed
    public ResponseEntity<SysProduct> getSysProduct(@PathVariable Long id) {
        log.debug("REST request to get SysProduct : {}", id);
        Optional<SysProduct> sysProduct = sysProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysProduct);
    }

    /**
     * DELETE  /sys-products/:id : delete the "id" sysProduct.
     *
     * @param id the id of the sysProduct to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysProduct(@PathVariable Long id) {
        log.debug("REST request to delete SysProduct : {}", id);
        sysProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
