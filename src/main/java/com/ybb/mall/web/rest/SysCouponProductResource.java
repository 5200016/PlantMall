package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysCouponProduct;
import com.ybb.mall.service.SysCouponProductService;
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
 * REST controller for managing SysCouponProduct.
 */
@RestController
@RequestMapping("/api")
public class SysCouponProductResource {

    private final Logger log = LoggerFactory.getLogger(SysCouponProductResource.class);

    private static final String ENTITY_NAME = "sysCouponProduct";

    private final SysCouponProductService sysCouponProductService;

    public SysCouponProductResource(SysCouponProductService sysCouponProductService) {
        this.sysCouponProductService = sysCouponProductService;
    }

    /**
     * POST  /sys-coupon-products : Create a new sysCouponProduct.
     *
     * @param sysCouponProduct the sysCouponProduct to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysCouponProduct, or with status 400 (Bad Request) if the sysCouponProduct has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-coupon-products")
    @Timed
    public ResponseEntity<SysCouponProduct> createSysCouponProduct(@RequestBody SysCouponProduct sysCouponProduct) throws URISyntaxException {
        log.debug("REST request to save SysCouponProduct : {}", sysCouponProduct);
        if (sysCouponProduct.getId() != null) {
            throw new BadRequestAlertException("A new sysCouponProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysCouponProduct result = sysCouponProductService.save(sysCouponProduct);
        return ResponseEntity.created(new URI("/api/sys-coupon-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-coupon-products : Updates an existing sysCouponProduct.
     *
     * @param sysCouponProduct the sysCouponProduct to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysCouponProduct,
     * or with status 400 (Bad Request) if the sysCouponProduct is not valid,
     * or with status 500 (Internal Server Error) if the sysCouponProduct couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-coupon-products")
    @Timed
    public ResponseEntity<SysCouponProduct> updateSysCouponProduct(@RequestBody SysCouponProduct sysCouponProduct) throws URISyntaxException {
        log.debug("REST request to update SysCouponProduct : {}", sysCouponProduct);
        if (sysCouponProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysCouponProduct result = sysCouponProductService.save(sysCouponProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysCouponProduct.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-coupon-products : get all the sysCouponProducts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysCouponProducts in body
     */
    @GetMapping("/sys-coupon-products")
    @Timed
    public List<SysCouponProduct> getAllSysCouponProducts() {
        log.debug("REST request to get all SysCouponProducts");
        return sysCouponProductService.findAll();
    }

    /**
     * GET  /sys-coupon-products/:id : get the "id" sysCouponProduct.
     *
     * @param id the id of the sysCouponProduct to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysCouponProduct, or with status 404 (Not Found)
     */
    @GetMapping("/sys-coupon-products/{id}")
    @Timed
    public ResponseEntity<SysCouponProduct> getSysCouponProduct(@PathVariable Long id) {
        log.debug("REST request to get SysCouponProduct : {}", id);
        Optional<SysCouponProduct> sysCouponProduct = sysCouponProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysCouponProduct);
    }

    /**
     * DELETE  /sys-coupon-products/:id : delete the "id" sysCouponProduct.
     *
     * @param id the id of the sysCouponProduct to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-coupon-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysCouponProduct(@PathVariable Long id) {
        log.debug("REST request to delete SysCouponProduct : {}", id);
        sysCouponProductService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
