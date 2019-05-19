/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.PatientDoctorDO;

/**
 * Database Access Object for patientDoctor table.
 * <p/>
 */
public interface PatientDoctorRepository extends CrudRepository<PatientDoctorDO, Long>
{
	/**
	 * 
	 * @param patientId
	 * @return
	 */
	PatientDoctorDO findById(long patientId);
}
