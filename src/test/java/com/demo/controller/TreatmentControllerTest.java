package com.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.ObjectInitializerForTest;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.domainobject.PatientRoomDO;
import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;
import com.demo.service.DoctorService;
import com.demo.service.PatientDoctorService;
import com.demo.service.PatientRoomService;
import com.demo.service.PatientService;
import com.demo.service.RoomService;
import com.demo.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TreatmentControllerTest extends ObjectInitializerForTest{

	@Autowired
	@InjectMocks
    private static TreatmentController treatmentController;
    
    
    private static PatientService mockedPatientService;
    private static DoctorService mockedDoctorService;
    private static RoomService mockedRoomService;
    private static PatientDoctorService mockedPatientDoctorService;
    private static PatientRoomService mockedPatientRoomService;
    private static ScheduleService mockScheduleService;
    private static BindingResult mockedBindingResult;
   

    @BeforeClass
    public static void setUpUserControllerInstance() {
    	mockedPatientService = mock(PatientService.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedDoctorService = mock(DoctorService.class);
        mockedRoomService = mock(RoomService.class);
        mockedPatientDoctorService = mock(PatientDoctorService.class);
        mockedPatientRoomService = mock(PatientRoomService.class);
        mockScheduleService = mock(ScheduleService.class);
        treatmentController = new TreatmentController();
    }

    @Test
    public void whenCalledaddPatientAndValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView modelAndView = new ModelAndView("patientDetails");
    	String message = "Patient Id:  " + patient.getId() + " has been successfully added";
		modelAndView.addObject("message", message);
		modelAndView.addObject("patientDO", patient);
    	
    	
        when(mockedBindingResult.hasErrors()).thenReturn(false);
        when(mockedDoctorService.findDoctorByName(Matchers.anyString())).thenReturn(doctor);
        when(mockedRoomService.getRoomByName(Matchers.anyString())).thenReturn(room);
        when(mockedPatientDoctorService.assignDoctorToPatient(Matchers.any(PatientDoctorDO.class))).thenReturn(patientDoctorDO);
        when(mockedPatientRoomService.assignPatientRoom(Matchers.any(PatientRoomDO.class))).thenReturn(patientRoomDO);
        
        
        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(treatmentController.addPatient(patient, mockedBindingResult).toString()).isEqualTo(modelAndView.toString());
    }

    @Test
    public void whenCalledaddPatientAndInValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView errors = new ModelAndView("addPatient");
    	patient.setName(null);
        when(mockedBindingResult.hasErrors()).thenReturn(true);        
        assertThat(treatmentController.addPatient(patient, mockedBindingResult).toString()).isEqualTo(errors.toString());
    }
   
    @Test
    public void whenCalledupdateProcedureAndValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView modelAndView = new ModelAndView("updateProcedureDetails");
    	String message = "Procedure id: " + study.getId() + " has been successfully edited";
    	modelAndView.addObject("message", message);
    	modelAndView.addObject("patient", study.getPatientDetails());
    	modelAndView.addObject("study", study);
    	
    	when(mockedPatientService.findPatientById(Matchers.any(Long.class))).thenReturn(patient);
    	when(mockScheduleService.createSchedule(Matchers.any(StudyDO.class))).thenReturn(study);
        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(treatmentController.updateProcedure(study, mockedBindingResult, "save").toString()).isEqualTo(modelAndView.toString());//(1l, user, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledupdateProcedureAndInValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView modelAndView = new ModelAndView("updateScheduleProcedure");
    	study.setDescription(null);

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(treatmentController.updateProcedure(study, mockedBindingResult, "save").toString()).isEqualTo(modelAndView.toString());
    }
    
    @Test
    public void whenCalledcreateProcedureAndValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView modelAndView = new ModelAndView("procedureDetails");
    	String message = "Procedure id: " + study.getId() + " has been successfully added";
    	modelAndView.addObject("message", message);
    	modelAndView.addObject("study", study);
    	modelAndView.addObject("patientDO", study.getPatientDetails());
    	
    	when(mockedPatientService.findPatientById(Matchers.any(Long.class))).thenReturn(patient);
    	when(mockScheduleService.createSchedule(Matchers.any(StudyDO.class))).thenReturn(study);

        when(mockedBindingResult.hasErrors()).thenReturn(false);

        assertThat(treatmentController.scheduleProcedure(study, mockedBindingResult).toString()).isEqualTo(modelAndView.toString());//(1l, user, mockedBindingResult, mockedModel)).isEqualTo("index");
    }

    @Test
    public void whenCalledcreateUserAndInValidData_thenCorrect() throws ConstraintsViolationException, EntityNotFoundException {
    	ModelAndView modelAndView = new ModelAndView("updateScheduleProcedure");
    	study.setDescription(null);
        when(mockedBindingResult.hasErrors()).thenReturn(true);
        assertThat(treatmentController.updateProcedure(study, mockedBindingResult, "save").toString()).isEqualTo(modelAndView.toString());
    }
}