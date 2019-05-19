/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.StudyDO;

/**
 * Database Access Object for schedule table.
 * <p/>
 */
public interface ScheduleRepository extends CrudRepository<StudyDO, Long>{
	/**
	 * 
	 * @param studyDO
	 * @return
	 */
	StudyDO save(StudyDO studyDO);

	/**
	 * 
	 * @param id
	 * @return
	 */
	
	StudyDO findById(long id);

}
