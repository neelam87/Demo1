/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.PatientRoomDO;

/**
 * Database Access Object for patient room table.
 * <p/>
 */
public interface PatientRoomRepository extends CrudRepository<PatientRoomDO, Long>
{
	/**
	 * 
	 * @param id
	 * @return
	 */
	PatientRoomDO findById(long id);
}
