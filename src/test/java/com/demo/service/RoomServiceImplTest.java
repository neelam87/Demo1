/**
 * 
 */
package com.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.ObjectInitializerForTest;
import com.demo.dataaccessobject.DoctorRepository;
import com.demo.dataaccessobject.RoomRepository;
import com.demo.domainobject.DoctorDO;
import com.demo.domainobject.RoomDO;
import com.demo.exception.EntityNotFoundException;

/**
 * @author x169185
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomServiceImplTest extends ObjectInitializerForTest{
	
	@Autowired
	@InjectMocks
    private static RoomServiceImpl roomService;
	
	private static RoomRepository mockedRoomRepository;
    

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockedRoomRepository = mock(RoomRepository.class);
		roomService = new RoomServiceImpl();
	}
	
	
	/**
	 * Test method for {@link com.demo.service.RoomServiceImpl#getRoomByName(java.lang.String)}.
	 */
	@Test
	public void testGetRoomByName() throws EntityNotFoundException {
		when(mockedRoomRepository.findByName(Matchers.anyString())).thenReturn(room);
		String roomRoom = room.getName();
		RoomDO room = roomService.getRoomByName(roomRoom);
		assertThat(room.getName()).isEqualTo("Room8");
		
	}

}
