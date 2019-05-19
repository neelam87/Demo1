package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.PatientDoctorRepository;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
@Service
public class PatientDoctorServiceImpl implements PatientDoctorService{
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;

	public PatientDoctorDO assignDoctorToPatient(PatientDoctorDO patientDoctorDO) throws ConstraintsViolationException {
		PatientDoctorDO patientDoctor;
		try {
			patientDoctor = patientDoctorRepository.save(patientDoctorDO);
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintsViolationException(e.getMessage());
		}
		return patientDoctor;
	}
	
	public PatientDoctorDO findPatientById(long patientId) throws EntityNotFoundException {
		return patientDoctorRepository.findById(patientId);
				
	}


}
