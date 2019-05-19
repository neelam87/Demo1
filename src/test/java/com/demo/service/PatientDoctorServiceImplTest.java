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
import com.demo.dataaccessobject.PatientDoctorRepository;
import com.demo.dataaccessobject.PatientRepository;
import com.demo.dataaccessobject.PatientRoomRepository;
import com.demo.domainobject.PatientDO;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.domainobject.PatientRoomDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author x169185
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PatientDoctorServiceImplTest extends ObjectInitializerForTest{

	@Autowired
	@InjectMocks
    private static PatientDoctorServiceImpl patientDoctorService;
	
	private static PatientDoctorRepository mockPatientDoctorRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockPatientDoctorRepository = mock(PatientDoctorRepository.class);
		patientDoctorService = new PatientDoctorServiceImpl();
	}

	/**
	 * Test method for {@link com.demo.service.PatientDoctorServiceImpl#assignPatientDoctor(com.demo.domainobject.PatientDoctorDO)}.
	 * @throws ConstraintsViolationException 
	 */
	@Test
	public void testAssignPatientRoom() throws ConstraintsViolationException {
		when(mockPatientDoctorRepository.save(Matchers.any(PatientDoctorDO.class))).thenReturn(patientDoctorDO);
		assertThat(patientDoctorService.assignDoctorToPatient(patientDoctorDO)).isEqualTo(patientDoctorDO);
	}

	/**
	 * 
	 * @throws EntityNotFoundException
	 */
	
	@Test
	public void testFindIfRoomAvailable() throws EntityNotFoundException {
		when(mockPatientDoctorRepository.findById(Matchers.anyLong())).thenReturn(patientDoctorDO);
		PatientDoctorDO patientDoctorDO =patientDoctorService.findPatientById(patientRoomDO.getId());
		assertThat(patientDoctorDO.getId()).isEqualTo(41l);
	}

}
