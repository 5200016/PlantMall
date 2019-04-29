package com.ybb.mall.service;

import com.ybb.mall.domain.SysAppointment;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SysAppointment.
 */
public interface SysAppointmentService {

    /**
     * Save a sysAppointment.
     *
     * @param sysAppointment the entity to save
     * @return the persisted entity
     */
    SysAppointment save(SysAppointment sysAppointment);

    /**
     * Get all the sysAppointments.
     *
     * @return the list of entities
     */
    List<SysAppointment> findAll();


    /**
     * Get the "id" sysAppointment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SysAppointment> findOne(Long id);

    /**
     * Delete the "id" sysAppointment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
