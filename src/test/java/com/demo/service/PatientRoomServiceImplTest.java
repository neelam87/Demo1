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
import com.demo.dataaccessobject.PatientRepository;
import com.demo.dataaccessobject.PatientRoomRepository;
import com.demo.domainobject.PatientDO;
import com.demo.domainobject.PatientRoomDO;
import com.demo.exception.ConstraintsViolationException;

/**
 * @author x169185
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PatientRoomServiceImplTest extends ObjectInitializerForTest{

	@Autowired
	@InjectMocks
    private static PatientRoomServiceImpl patientRoomService;
	
	private static PatientRoomRepository mockPatientRoomRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockPatientRoomRepository = mock(PatientRoomRepository.class);
		patientRoomService = new PatientRoomServiceImpl();
	}

	/**
	 * Test method for {@link com.demo.service.PatientRoomServiceImpl#assignPatientRoom(com.demo.domainobject.PatientRoomDO)}.
	 * @throws ConstraintsViolationException 
	 */
	@Test
	public void testAssignPatientRoom() throws ConstraintsViolationException {
		when(mockPatientRoomRepository.save(Matchers.any(PatientRoomDO.class))).thenReturn(patientRoomDO);
		assertThat(patientRoomService.assignPatientRoom(patientRoomDO)).isEqualTo(patientRoomDO);
	}

	/**
	 * Test method for {@link com.demo.service.PatientRoomServiceImpl#findIfRoomAvailable(java.lang.Long)}.
	 */
	@Test
	public void testFindIfRoomAvailable() {
		when(mockPatientRoomRepository.findById(Matchers.anyLong())).thenReturn(patientRoomDO);
		assertThat(patientRoomService.findIfRoomAvailable(patientRoomDO.getId())).isEqualTo(null);
	}

}
