/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.DoctorDO;

/**
 * Database Access Object for doctor table.
 * <p/>
 */
public interface DoctorRepository extends CrudRepository<DoctorDO, String>
{
	/**
	 * 
	 * @param doctorName
	 * @return
	 */
	DoctorDO findByName(String doctorName);
}
