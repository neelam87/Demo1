/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.PatientDO;

/**
 * Database Access Object for patient table.
 * <p/>
 */
public interface PatientRepository extends CrudRepository<PatientDO, Long>
{
	/**
	 * 
	 * @param patientId
	 * @return
	 */
    PatientDO findById(long patientId);
}
