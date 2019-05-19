package com.demo.service;

import com.demo.domainobject.PatientDoctorDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
public interface PatientDoctorService {

	/**
	 * 
	 * @param patientDoctorDO
	 * @return
	 * @throws ConstraintsViolationException
	 */
	PatientDoctorDO assignDoctorToPatient(PatientDoctorDO patientDoctorDO) throws ConstraintsViolationException;
	/**
	 * 
	 * @param patientId
	 * @return
	 * @throws EntityNotFoundException
	 */
	PatientDoctorDO findPatientById(long patientId) throws EntityNotFoundException;
}
