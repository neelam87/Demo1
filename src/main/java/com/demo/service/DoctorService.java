package com.demo.service;

import com.demo.domainobject.DoctorDO;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.domainobject.RoomDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
public interface DoctorService {

	
	/**
	 * 
	 * @param doctorName
	 * @return
	 * @throws EntityNotFoundException
	 */
	DoctorDO findDoctorByName(String doctorName) throws EntityNotFoundException;
}
