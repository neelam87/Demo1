package com.demo.service;

import com.demo.domainobject.RoomDO;

/**
 * @author neelam
 *
 */
public interface RoomService {

	/**
	 * 
	 * @param roomName
	 * @return
	 */
	RoomDO getRoomByName(String roomName);
}
