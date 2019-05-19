/**
 * 
 */
package com.demo.dataaccessobject;

import org.springframework.data.repository.CrudRepository;

import com.demo.domainobject.RoomDO;

/**
 * Database Access Object for room table.
 * <p/>
 */
public interface RoomRepository extends CrudRepository<RoomDO, String>
{
    RoomDO findByName(String name);
}
