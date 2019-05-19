package com.demo.service;

import java.util.Optional;

import com.demo.domainobject.PatientRoomDO;
import com.demo.exception.ConstraintsViolationException;

/**
 * @author neelam
 *
 */
public interface PatientRoomService {

	/**
	 * 
	 * @param patientRoomDO
	 * @return
	 * @throws ConstraintsViolationException
	 */
    PatientRoomDO assignPatientRoom(PatientRoomDO patientRoomDO) throws ConstraintsViolationException;
    /**
     * 
     * @param roomId
     * @return
     */
    Optional<PatientRoomDO> findIfRoomAvailable(Long roomId);
}
