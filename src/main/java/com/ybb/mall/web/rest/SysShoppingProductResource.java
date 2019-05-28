package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysShoppingProduct;
import com.ybb.mall.service.SysShoppingProductService;
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
 * REST controller for managing SysShoppingProduct.
 */
@RestController
@RequestMapping("/api")
public class SysShoppingProductResource {

    private final Logger log = LoggerFactory.getLogger(SysShoppingProductResource.class);

    private static final String ENTITY_NAME = "sysShoppingProduct";

    private final SysShoppingProductService sysShoppingProductService;

    public SysShoppingProductResource(SysShoppingProductService sysShoppingProductService) {
        this.sysShoppingProductService = sysShoppingProductService;
    }

    /**
     * POST  /sys-shopping-products : Create a new sysShoppingProduct.
     *
     * @param sysShoppingProduct the sysShoppingProduct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysShoppingProduct, or with status 400 (Bad Request) if the sysShoppingProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-shopping-products")
    @Timed
    public ResponseEntity<SysShoppingProduct> createSysShoppingProduct(@RequestBody SysShoppingProduct sysShoppingProduct) throws URISyntaxException {
        log.debug("REST request to save SysShoppingProduct : {}", sysShoppingProduct);
        if (sysShoppingProduct.getId() != null) {
            throw new BadRequestAlertException("A new sysShoppingProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysShoppingProduct result = sysShoppingProductService.save(sysShoppingProduct);
        return ResponseEntity.created(new URI("/api/sys-shopping-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-shopping-products : Updates an existing sysShoppingProduct.
     *
     * @param sysShoppingProduct the sysShoppingProduct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysShoppingProduct,
     * or with status 400 (Bad Request) if the sysShoppingProduct is not valid,
     * or with status 500 (Internal Server Error) if the sysShoppingProduct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-shopping-products")
    @Timed
    public ResponseEntity<SysShoppingProduct> updateSysShoppingProduct(@RequestBody SysShoppingProduct sysShoppingProduct) throws URISyntaxException {
        log.debug("REST request to update SysShoppingProduct : {}", sysShoppingProduct);
        if (sysShoppingProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysShoppingProduct result = sysShoppingProductService.save(sysShoppingProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysShoppingProduct.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-shopping-products : get all the sysShoppingProducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysShoppingProducts in body
     */
    @GetMapping("/sys-shopping-products")
    @Timed
    public List<SysShoppingProduct> getAllSysShoppingProducts() {
        log.debug("REST request to get all SysShoppingProducts");
        return sysShoppingProductService.findAll();
    }

    /**
     * GET  /sys-shopping-products/:id : get the "id" sysShoppingProduct.
     *
     * @param id the id of the sysShoppingProduct to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysShoppingProduct, or with status 404 (Not Found)
     */
    @GetMapping("/sys-shopping-products/{id}")
    @Timed
    public ResponseEntity<SysShoppingProduct> getSysShoppingProduct(@PathVariable Long id) {
        log.debug("REST request to get SysShoppingProduct : {}", id);
        Optional<SysShoppingProduct> sysShoppingProduct = sysShoppingProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysShoppingProduct);
    }

    /**
     * DELETE  /sys-shopping-products/:id : delete the "id" sysShoppingProduct.
     *
     * @param id the id of the sysShoppingProduct to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-shopping-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysShoppingProduct(@PathVariable Long id) {
        log.debug("REST request to delete SysShoppingProduct : {}", id);
        sysShoppingProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
