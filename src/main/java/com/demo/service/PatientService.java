package com.demo.service;

import com.demo.domainobject.PatientDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam.awasthi
 *
 */
public interface PatientService {

	PatientDO addPatient(PatientDO patientDO) throws ConstraintsViolationException;

	PatientDO findPatientById(Long id) throws EntityNotFoundException;
}
