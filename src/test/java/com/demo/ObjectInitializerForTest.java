package com.demo;

import java.util.Date;

import org.junit.Before;

import com.demo.domainobject.DoctorDO;
import com.demo.domainobject.PatientDO;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.domainobject.PatientRoomDO;
import com.demo.domainobject.RoomDO;
import com.demo.domainobject.StudyDO;
import com.demo.domainobject.StudyDO.State;

public class ObjectInitializerForTest {
	protected PatientDO patient;
	protected DoctorDO doctor;
	protected RoomDO room;
	protected PatientDoctorDO patientDoctorDO;
	protected PatientRoomDO patientRoomDO;
	protected StudyDO study;
	
	@Before
	public void setUp() throws Exception {
		patient = new PatientDO();
    	patient.setId(001l);
    	patient.setName("testPatient");
    	patient.setSex("Female");
    	patient.setDateOfBirth(new Date());
    	
    	doctor = new DoctorDO();
    	doctor.setId(41l);
    	doctor.setName("Doc41");
    	
    	room = new RoomDO();
    	room.setId(41l);
    	room.setName("Room8");
    	
    	patientDoctorDO = new PatientDoctorDO(); 
    	patientDoctorDO.setId(41l);
    	patientDoctorDO.setDoctorId(41l);
    	patientDoctorDO.setPatientId(41l);
    	
    	patientRoomDO = new PatientRoomDO();
    	patientRoomDO.setId(41l);
    	patientRoomDO.setPatientId(41l);
    	patientRoomDO.setRoomId(41l);    	
    	 
    	patient.setDoctorDetails(doctor);
    	patient.setRoomDetails(room);
    	

    	study = new StudyDO();
    	study.setId(41l);
    	study.setDescription("I am feeling good");
    	study.setPlannedStartTime(new Date());
    	study.setEstimatedEndTime(new Date());
    	study.setState(State.INPROGRESS);
    	
    	study.setPatientDetails(patient);
	}

}
