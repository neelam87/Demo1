package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dataaccessobject.RoomRepository;
import com.demo.domainobject.RoomDO;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 */
@Service
public class RoomServiceImpl implements RoomService{
	@Autowired
	private RoomRepository roomRepository;

	/**
	 * Selects a room by id.
	 *
	 * @param id
	 * @return found room details
	 * @throws EntityNotFoundException
	 *             if no room with the given id was found.
	 */
	public RoomDO getRoomByName(String name){
		return roomRepository.findByName(name);
	}

}
