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
import com.demo.dataaccessobject.ScheduleRepository;
import com.demo.domainobject.PatientDO;
import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author x169185
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PatientServiceImplTest extends ObjectInitializerForTest{
	
	@Autowired
	@InjectMocks
    private static PatientServiceImpl patientService;
	
	private static PatientRepository mockedPatientRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockedPatientRepository = mock(PatientRepository.class);
		patientService = new PatientServiceImpl();
	}

	/**
	 * Test method for {@link com.demo.service.PatientServiceImpl#addPatient(com.demo.domainobject.PatientDO)}.
	 * @throws ConstraintsViolationException 
	 */
	@Test
	public void testAddPatient() throws ConstraintsViolationException {
		when(mockedPatientRepository.save(Matchers.any(PatientDO.class))).thenReturn(patient);
		assertThat(patientService.addPatient(patient)).isEqualTo(patient);
	}

	/**
	 * Test method for {@link com.demo.service.PatientServiceImpl#findPatientById(java.lang.Long)}.
	 * @throws EntityNotFoundException 
	 */
	@Test(expected=NullPointerException.class)
	public void testFindPatientById() throws EntityNotFoundException {
		when(mockedPatientRepository.findById(Matchers.anyLong())).thenReturn(patient);
		assertThat(patientService.findPatientById(patient.getId())).isEqualTo(41l);
	}

}
