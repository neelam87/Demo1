package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.DoctorRepository;
import com.demo.domainobject.DoctorDO;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;
	
	
		
	/**
	 * 
	 * @param doctorName
	 * @return
	 * @throws EntityNotFoundException
	 */
	public DoctorDO findDoctorByName(String doctorName) throws EntityNotFoundException {
		return doctorRepository.findByName(doctorName);
	}
}
