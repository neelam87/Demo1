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
import com.demo.controller.TreatmentController;
import com.demo.dataaccessobject.DoctorRepository;
import com.demo.domainobject.DoctorDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author neelam
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoctorServiceImplTest extends ObjectInitializerForTest{
	
	@Autowired
	@InjectMocks
    private static DoctorServiceImpl doctorService;
	
	private static DoctorRepository mockedDoctorRepository;
    

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockedDoctorRepository = mock(DoctorRepository.class);
		doctorService = new DoctorServiceImpl();
	}

	/**
	 * Test method for {@link com.demo.service.DoctorServiceImpl#findDoctorByName(java.lang.String)}.
	 * @throws EntityNotFoundException 
	 */
	@Test
	public void testFindDoctorByName() throws EntityNotFoundException {
		when(mockedDoctorRepository.findByName(Matchers.anyString())).thenReturn(doctor);
		String doctorName = doctor.getName();
		DoctorDO doctor = doctorService.findDoctorByName(doctorName);
		assertThat(doctor.getName()).isEqualTo("Doc41");
		
	}
	
	

}
