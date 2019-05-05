package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysOrderProduct;
import com.ybb.mall.service.SysOrderProductService;
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
 * REST controller for managing SysOrderProduct.
 */
@RestController
@RequestMapping("/api")
public class SysOrderProductResource {

    private final Logger log = LoggerFactory.getLogger(SysOrderProductResource.class);

    private static final String ENTITY_NAME = "sysOrderProduct";

    private final SysOrderProductService sysOrderProductService;

    public SysOrderProductResource(SysOrderProductService sysOrderProductService) {
        this.sysOrderProductService = sysOrderProductService;
    }

    /**
     * POST  /sys-order-products : Create a new sysOrderProduct.
     *
     * @param sysOrderProduct the sysOrderProduct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysOrderProduct, or with status 400 (Bad Request) if the sysOrderProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-order-products")
    @Timed
    public ResponseEntity<SysOrderProduct> createSysOrderProduct(@RequestBody SysOrderProduct sysOrderProduct) throws URISyntaxException {
        log.debug("REST request to save SysOrderProduct : {}", sysOrderProduct);
        if (sysOrderProduct.getId() != null) {
            throw new BadRequestAlertException("A new sysOrderProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysOrderProduct result = sysOrderProductService.save(sysOrderProduct);
        return ResponseEntity.created(new URI("/api/sys-order-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-order-products : Updates an existing sysOrderProduct.
     *
     * @param sysOrderProduct the sysOrderProduct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysOrderProduct,
     * or with status 400 (Bad Request) if the sysOrderProduct is not valid,
     * or with status 500 (Internal Server Error) if the sysOrderProduct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-order-products")
    @Timed
    public ResponseEntity<SysOrderProduct> updateSysOrderProduct(@RequestBody SysOrderProduct sysOrderProduct) throws URISyntaxException {
        log.debug("REST request to update SysOrderProduct : {}", sysOrderProduct);
        if (sysOrderProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysOrderProduct result = sysOrderProductService.save(sysOrderProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysOrderProduct.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-order-products : get all the sysOrderProducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysOrderProducts in body
     */
    @GetMapping("/sys-order-products")
    @Timed
    public List<SysOrderProduct> getAllSysOrderProducts() {
        log.debug("REST request to get all SysOrderProducts");
        return sysOrderProductService.findAll();
    }

    /**
     * GET  /sys-order-products/:id : get the "id" sysOrderProduct.
     *
     * @param id the id of the sysOrderProduct to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysOrderProduct, or with status 404 (Not Found)
     */
    @GetMapping("/sys-order-products/{id}")
    @Timed
    public ResponseEntity<SysOrderProduct> getSysOrderProduct(@PathVariable Long id) {
        log.debug("REST request to get SysOrderProduct : {}", id);
        Optional<SysOrderProduct> sysOrderProduct = sysOrderProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysOrderProduct);
    }

    /**
     * DELETE  /sys-order-products/:id : delete the "id" sysOrderProduct.
     *
     * @param id the id of the sysOrderProduct to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-order-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysOrderProduct(@PathVariable Long id) {
        log.debug("REST request to delete SysOrderProduct : {}", id);
        sysOrderProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
