package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.ScheduleRepository;
import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
@Service
public class ScheduleServiceImpl implements ScheduleService{
	@Autowired
	private ScheduleRepository scheduleRepository;

	public StudyDO createSchedule(StudyDO studyDO) throws ConstraintsViolationException {
		StudyDO study;
		try {
			study = scheduleRepository.save(studyDO);
		} catch (DataIntegrityViolationException e) {
			throw new ConstraintsViolationException(e.getMessage());
		}
		return study;
	}

	
	public StudyDO findScheduleById(Long Id) throws EntityNotFoundException {
		return scheduleRepository.findById(Id)
				.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + Id));
	}
}
