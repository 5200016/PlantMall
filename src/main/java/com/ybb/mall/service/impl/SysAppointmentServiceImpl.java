package com.ybb.mall.service.impl;

import com.ybb.mall.service.SysAppointmentService;
import com.ybb.mall.domain.SysAppointment;
import com.ybb.mall.repository.SysAppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing SysAppointment.
 */
@Service
@Transactional
public class SysAppointmentServiceImpl implements SysAppointmentService {

    private final Logger log = LoggerFactory.getLogger(SysAppointmentServiceImpl.class);

    private final SysAppointmentRepository sysAppointmentRepository;

    public SysAppointmentServiceImpl(SysAppointmentRepository sysAppointmentRepository) {
        this.sysAppointmentRepository = sysAppointmentRepository;
    }

    /**
     * Save a sysAppointment.
     *
     * @param sysAppointment the entity to save
     * @return the persisted entity
     */
    @Override
    public SysAppointment save(SysAppointment sysAppointment) {
        log.debug("Request to save SysAppointment : {}", sysAppointment);
        return sysAppointmentRepository.save(sysAppointment);
    }

    /**
     * Get all the sysAppointments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SysAppointment> findAll() {
        log.debug("Request to get all SysAppointments");
        return sysAppointmentRepository.findAll();
    }


    /**
     * Get one sysAppointment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SysAppointment> findOne(Long id) {
        log.debug("Request to get SysAppointment : {}", id);
        return sysAppointmentRepository.findById(id);
    }

    /**
     * Delete the sysAppointment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SysAppointment : {}", id);
        sysAppointmentRepository.deleteById(id);
    }
}
