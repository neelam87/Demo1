/**
 * 
 */
package com.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.demo.dataaccessobject.RoomRepository;
import com.demo.dataaccessobject.ScheduleRepository;
import com.demo.domainobject.RoomDO;
import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;

/**
 * @author x169185
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ScheduleServiceImplTest extends ObjectInitializerForTest{
	
	@Autowired
	@InjectMocks
    private static ScheduleServiceImpl scheduleService;
	
	private static ScheduleRepository mockedScheduleRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpUserControllerInstance() {
		mockedScheduleRepository = mock(ScheduleRepository.class);
		scheduleService = new ScheduleServiceImpl();
	}
	/**
	 * Test method for {@link com.demo.service.ScheduleServiceImpl#createSchedule(com.demo.domainobject.StudyDO)}.
	 * @throws ConstraintsViolationException 
	 */
	@Test
	public void testCreateSchedule() throws ConstraintsViolationException {
		when(mockedScheduleRepository.save(Matchers.any(StudyDO.class))).thenReturn(study);
		assertThat(scheduleService.createSchedule(study)).isEqualTo(study);
	}

	/**
	 * Test method for {@link com.demo.service.ScheduleServiceImpl#findScheduleById(java.lang.Long)}.
	 * 
	*/
	@Test(expected=NullPointerException.class)
	public void testFindScheduleById() throws EntityNotFoundException {
		when(mockedScheduleRepository.findById(Matchers.anyLong())).thenReturn(study);
		assertThat(scheduleService.findScheduleById(study.getId())).isEqualTo(41l);
		
	}

}
