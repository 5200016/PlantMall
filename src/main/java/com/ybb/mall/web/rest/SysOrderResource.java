package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysOrder;
import com.ybb.mall.service.SysOrderService;
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
 * REST controller for managing SysOrder.
 */
@RestController
@RequestMapping("/api")
public class SysOrderResource {

    private final Logger log = LoggerFactory.getLogger(SysOrderResource.class);

    private static final String ENTITY_NAME = "sysOrder";

    private final SysOrderService sysOrderService;

    public SysOrderResource(SysOrderService sysOrderService) {
        this.sysOrderService = sysOrderService;
    }

    /**
     * POST  /sys-orders : Create a new sysOrder.
     *
     * @param sysOrder the sysOrder to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysOrder, or with status 400 (Bad Request) if the sysOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-orders")
    @Timed
    public ResponseEntity<SysOrder> createSysOrder(@RequestBody SysOrder sysOrder) throws URISyntaxException {
        log.debug("REST request to save SysOrder : {}", sysOrder);
        if (sysOrder.getId() != null) {
            throw new BadRequestAlertException("A new sysOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysOrder result = sysOrderService.save(sysOrder);
        return ResponseEntity.created(new URI("/api/sys-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-orders : Updates an existing sysOrder.
     *
     * @param sysOrder the sysOrder to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysOrder,
     * or with status 400 (Bad Request) if the sysOrder is not valid,
     * or with status 500 (Internal Server Error) if the sysOrder couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-orders")
    @Timed
    public ResponseEntity<SysOrder> updateSysOrder(@RequestBody SysOrder sysOrder) throws URISyntaxException {
        log.debug("REST request to update SysOrder : {}", sysOrder);
        if (sysOrder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysOrder result = sysOrderService.save(sysOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysOrder.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-orders : get all the sysOrders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysOrders in body
     */
    @GetMapping("/sys-orders")
    @Timed
    public List<SysOrder> getAllSysOrders() {
        log.debug("REST request to get all SysOrders");
        return sysOrderService.findAll();
    }

    /**
     * GET  /sys-orders/:id : get the "id" sysOrder.
     *
     * @param id the id of the sysOrder to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysOrder, or with status 404 (Not Found)
     */
    @GetMapping("/sys-orders/{id}")
    @Timed
    public ResponseEntity<SysOrder> getSysOrder(@PathVariable Long id) {
        log.debug("REST request to get SysOrder : {}", id);
        Optional<SysOrder> sysOrder = sysOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysOrder);
    }

    /**
     * DELETE  /sys-orders/:id : delete the "id" sysOrder.
     *
     * @param id the id of the sysOrder to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysOrder(@PathVariable Long id) {
        log.debug("REST request to delete SysOrder : {}", id);
        sysOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
