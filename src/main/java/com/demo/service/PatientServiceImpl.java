package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.PatientRepository;
import com.demo.domainobject.PatientDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
@Service
public class PatientServiceImpl implements PatientService{
	@Autowired	
	private PatientRepository patientRepository;

	
	/**
	 * 
	 * @param patientDO
	 * @return
	 * @throws ConstraintsViolationException
	 */
	public PatientDO addPatient(PatientDO patientDO) throws ConstraintsViolationException {
		PatientDO patient;
		try {
			patient = patientRepository.save(patientDO);
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintsViolationException(e.getMessage());
		}
		return patient;
	}

	/**
	 * Selects a patient by id.
	 *
	 * @param id
	 * @return found patient details
	 * @throws EntityNotFoundException
	 *             if no room with the given id was found.
	 */
	public PatientDO findPatientById(Long Id) throws EntityNotFoundException {
		return patientRepository.findById(Id)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + Id));
	}

}
