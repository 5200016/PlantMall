package com.ybb.mall.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ybb.mall.domain.SysAppointment;
import com.ybb.mall.service.SysAppointmentService;
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
 * REST controller for managing SysAppointment.
 */
@RestController
@RequestMapping("/api")
public class SysAppointmentResource {

    private final Logger log = LoggerFactory.getLogger(SysAppointmentResource.class);

    private static final String ENTITY_NAME = "sysAppointment";

    private final SysAppointmentService sysAppointmentService;

    public SysAppointmentResource(SysAppointmentService sysAppointmentService) {
        this.sysAppointmentService = sysAppointmentService;
    }

    /**
     * POST  /sys-appointments : Create a new sysAppointment.
     *
     * @param sysAppointment the sysAppointment to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysAppointment, or with status 400 (Bad Request) if the sysAppointment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-appointments")
    @Timed
    public ResponseEntity<SysAppointment> createSysAppointment(@RequestBody SysAppointment sysAppointment) throws URISyntaxException {
        log.debug("REST request to save SysAppointment : {}", sysAppointment);
        if (sysAppointment.getId() != null) {
            throw new BadRequestAlertException("A new sysAppointment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SysAppointment result = sysAppointmentService.save(sysAppointment);
        return ResponseEntity.created(new URI("/api/sys-appointments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-appointments : Updates an existing sysAppointment.
     *
     * @param sysAppointment the sysAppointment to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysAppointment,
     * or with status 400 (Bad Request) if the sysAppointment is not valid,
     * or with status 500 (Internal Server Error) if the sysAppointment couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-appointments")
    @Timed
    public ResponseEntity<SysAppointment> updateSysAppointment(@RequestBody SysAppointment sysAppointment) throws URISyntaxException {
        log.debug("REST request to update SysAppointment : {}", sysAppointment);
        if (sysAppointment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SysAppointment result = sysAppointmentService.save(sysAppointment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysAppointment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-appointments : get all the sysAppointments.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysAppointments in body
     */
    @GetMapping("/sys-appointments")
    @Timed
    public List<SysAppointment> getAllSysAppointments() {
        log.debug("REST request to get all SysAppointments");
        return sysAppointmentService.findAll();
    }

    /**
     * GET  /sys-appointments/:id : get the "id" sysAppointment.
     *
     * @param id the id of the sysAppointment to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysAppointment, or with status 404 (Not Found)
     */
    @GetMapping("/sys-appointments/{id}")
    @Timed
    public ResponseEntity<SysAppointment> getSysAppointment(@PathVariable Long id) {
        log.debug("REST request to get SysAppointment : {}", id);
        Optional<SysAppointment> sysAppointment = sysAppointmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sysAppointment);
    }

    /**
     * DELETE  /sys-appointments/:id : delete the "id" sysAppointment.
     *
     * @param id the id of the sysAppointment to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-appointments/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysAppointment(@PathVariable Long id) {
        log.debug("REST request to delete SysAppointment : {}", id);
        sysAppointmentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
