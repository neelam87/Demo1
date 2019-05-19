package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.PatientRoomRepository;
import com.demo.domainobject.PatientRoomDO;
import com.demo.exception.ConstraintsViolationException;

/**
 * @author neelam
 *
 */
@Service
public class PatientRoomServiceImpl implements PatientRoomService{
	@Autowired
	private PatientRoomRepository patientRoomRepository;

	public PatientRoomDO assignPatientRoom(PatientRoomDO patientRoomDO) throws ConstraintsViolationException {
		PatientRoomDO patientRoom;
		try {
			patientRoom = patientRoomRepository.save(patientRoomDO);
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintsViolationException(e.getMessage());
		}
		return patientRoom;
	}
	
	public Optional<PatientRoomDO> findIfRoomAvailable(Long roomId){
		return patientRoomRepository.findById(roomId);
	}
	
	
}
