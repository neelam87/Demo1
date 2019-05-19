package com.demo.service;

import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam.awasthi
 *
 */
public interface ScheduleService {
	/**
	 * @param studyDO
	 * @return
	 * @throws ConstraintsViolationException
	 */
	StudyDO createSchedule(StudyDO studyDO) throws ConstraintsViolationException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	StudyDO findScheduleById(Long id) throws EntityNotFoundException;
}
