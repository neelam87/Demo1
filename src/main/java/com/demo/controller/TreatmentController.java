package com.demo.controller;

import com.demo.domainobject.DoctorDO;
import com.demo.domainobject.PatientDO;
import com.demo.domainobject.PatientDoctorDO;
import com.demo.domainobject.PatientRoomDO;
import com.demo.domainobject.RoomDO;
import com.demo.domainobject.StudyDO;
import com.demo.exception.ConstraintsViolationException;
import com.demo.exception.EntityNotFoundException;
import com.demo.service.DoctorService;
import com.demo.service.PatientDoctorService;
import com.demo.service.PatientRoomService;
import com.demo.service.PatientService;
import com.demo.service.RoomService;
import com.demo.service.ScheduleService;

import io.swagger.annotations.Api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author neelam.awasthi
 *
 *
 *         All operations with a treatment will be routed by this controller.
 *         <p/>
 */
@Controller
@RequestMapping("/demo/v1")
@Api(tags = "Demo", description = "demo services")
public class TreatmentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentController.class);
	
	@Autowired
	private PatientService patientService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private PatientDoctorService patientDoctorService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientRoomService patientRoomService;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	public String patient(Model model) {
		DoctorDO doctor = new DoctorDO();
		RoomDO room = new RoomDO();
		model.addAttribute("patientDO", new PatientDO(doctor, room));
		return "addPatient";
	}

	/**
	 * 
	 * @param patientDO
	 * @param bindingResult
	 * @return
	 * @throws ConstraintsViolationException
	 * @throws EntityNotFoundException
	 */
	@PostMapping(value = "/addPatient")
	public ModelAndView addPatient(@Valid @ModelAttribute(name = "patientDO") PatientDO patientDO,
			BindingResult bindingResult) throws ConstraintsViolationException, EntityNotFoundException {
		ModelAndView modelAndView = new ModelAndView("patientDetails");
		LOGGER.info("== == == == == BEGIN : Adding patient");
		String message = null;

		// check for errors
		if (bindingResult.hasErrors()) {
			LOGGER.error("Validation error while adding patient");
			ModelAndView errors = new ModelAndView("addPatient");
			return errors;
		}

		patientService.addPatient(patientDO);
		LOGGER.info("Patient has been added successfully "+patientDO.getId());

		if (!patientDO.getDoctorDetails().getName().isEmpty() && patientDO.getDoctorDetails().getName() != null) {
			DoctorDO doctorDetails = doctorService.findDoctorByName(patientDO.getDoctorDetails().getName());
			if (doctorDetails != null) {
				LOGGER.info("Doctor has been assigned to patient "+patientDO.getId());
				assignDoctor(patientDO, doctorDetails);
			}else {
				LOGGER.error("Doctor does not exists "+patientDO.getId());
				throw new EntityNotFoundException(patientDO.getDoctorDetails().getName() + " does not exists");
			}
		} 
		
		if (!patientDO.getRoomDetails().getName().isEmpty() && patientDO.getRoomDetails().getName() != null) {
		RoomDO roomDetails = roomService.getRoomByName(patientDO.getRoomDetails().getName());
		if (roomDetails != null) {
			LOGGER.info("Room has been assigned to patient, patient id: "+patientDO.getId());
			allocateRoom(patientDO, roomDetails);
		} else {
			LOGGER.error("Room does not exists, patient id: "+patientDO.getId());
			throw new EntityNotFoundException(patientDO.getRoomDetails().getName() + " does not exists");
		}
		}
		LOGGER.info("== == == == == End : Adding patient");
		message = "Patient Id:  " + patientDO.getId() + " has been successfully added";
		modelAndView.addObject("message", message);
		modelAndView.addObject("patientDO", patientDO);
		return modelAndView;
	}

	/**
	 * @param patientDO
	 * @param roomDetails
	 * @return
	 * @throws EntityNotFoundException
	 * @throws ConstraintsViolationException
	 */
	private PatientRoomDO allocateRoom(PatientDO patientDO, RoomDO roomDetails)
			throws EntityNotFoundException, ConstraintsViolationException {
		PatientRoomDO patientRoomDO = new PatientRoomDO();
		patientRoomDO.setPatientId(patientDO.getId());
		patientRoomDO.setRoomId(roomDetails.getId());
		patientRoomService.assignPatientRoom(patientRoomDO);

		return patientRoomDO;
	}

	/**
	 * @param patientDO
	 * @param doctorDetails
	 * @return
	 * @throws EntityNotFoundException
	 * @throws ConstraintsViolationException
	 */
	private PatientDoctorDO assignDoctor(PatientDO patientDO, DoctorDO doctorDetails)
			throws EntityNotFoundException, ConstraintsViolationException {
		PatientDoctorDO patientDoctorDO = new PatientDoctorDO();
		patientDoctorDO.setDoctorId(doctorDetails.getId());
		patientDoctorDO.setPatientId(patientDO.getId());
		patientDoctorService.assignDoctorToPatient(patientDoctorDO);
		return patientDoctorDO;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/procedure", method = RequestMethod.GET)
	public String procedure(Model model) {
		PatientDO patient = new PatientDO();
		StudyDO student = new StudyDO(patient);
		model.addAttribute("studyDO", student);
		return "scheduleProcedure";
	}

	/**
	 * 
	 * @param studyDO
	 * @param bindingResult
	 * @return
	 * @throws ConstraintsViolationException
	 * @throws EntityNotFoundException
	 */
	@PostMapping(value = "/scheduleProcedure")
	public ModelAndView scheduleProcedure(@Valid @ModelAttribute(name = "studyDO") StudyDO studyDO,
			BindingResult bindingResult) throws ConstraintsViolationException, EntityNotFoundException {
		LOGGER.info("== == == == == BEGIN : Schedule the procedure");
		// check for errors
		if (bindingResult.hasErrors()) {
			LOGGER.error("Validation error while adding patient");
			ModelAndView errors = new ModelAndView("scheduleProcedure");
			return errors;
		}
		ModelAndView modelAndView = new ModelAndView("procedureDetails");
		String message = null;

		PatientDO patientDetails = patientService.findPatientById(studyDO.getPatientDetails().getId());
		if(patientDetails!=null){
			studyDO.setPatientDetails(patientDetails);
			scheduleService.createSchedule(studyDO);
			LOGGER.info("Procedure has been scheduled successfully");

			message = "Procedure id: " + studyDO.getId() + " has been successfully added";
			modelAndView.addObject("message", message);
			modelAndView.addObject("study", studyDO);
			modelAndView.addObject("patientDO", studyDO.getPatientDetails());
			return modelAndView;
		}else{
			throw new EntityNotFoundException("Patient does not exists: "+ studyDO.getPatientDetails().getId());
		}
		
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	@RequestMapping(value = "/updateProcedure", method = RequestMethod.GET)
	public ModelAndView editProcedure(@RequestParam(value = "id", required = true) Integer id)
			throws EntityNotFoundException {
		ModelAndView modelAndView = new ModelAndView("updateScheduleProcedure");
		StudyDO studyDO = scheduleService.findScheduleById(Long.valueOf(id));
		modelAndView.addObject("studyDO", studyDO);
		return modelAndView;
	}

	/**
	 * 
	 * @param studyDO
	 * @param bindingResult
	 * @param action
	 * @return
	 * @throws ConstraintsViolationException
	 * @throws EntityNotFoundException
	 */
	@PostMapping(value = "/updateScheduleProcedure")
	public ModelAndView updateProcedure(@Valid @ModelAttribute(name = "studyDO") StudyDO studyDO,
			BindingResult bindingResult, @RequestParam(value = "action", required = true) String action)
			throws ConstraintsViolationException, EntityNotFoundException {
		LOGGER.info("== == == == == BEGIN : update the procedure");
		// check for errors
		if (bindingResult.hasErrors()) {
			LOGGER.error("Validation error while updating procedure");
			ModelAndView errors = new ModelAndView("updateScheduleProcedure");
			return errors;
		}
		ModelAndView modelAndView = new ModelAndView("updateProcedureDetails");
		String message = null;


		PatientDO patientDetails = patientService.findPatientById(studyDO.getPatientDetails().getId());
		if(patientDetails!=null){
		if (action.equals("save")) {
			updateProcedureDetails(studyDO, patientDetails);

			scheduleService.createSchedule(studyDO);
			message = "Procedure id: " + studyDO.getId() + " has been successfully edited";
			LOGGER.info("Procedure has been edited successfully");
			modelAndView.addObject("message", message);
			modelAndView.addObject("patient", patientDetails);
			modelAndView.addObject("study", studyDO);
		}
		return modelAndView;

	}
	else{
		throw new EntityNotFoundException("Patient does not exists: "+ studyDO.getPatientDetails().getId());
	}
	}

	/**
	 * @param studyDO
	 * @param patientDetails
	 */
	private void updateProcedureDetails(StudyDO studyDO, PatientDO patientDetails) {
		studyDO.setPatientDetails(patientDetails);
		studyDO.setDescription(studyDO.getDescription());
		studyDO.setPlannedStartTime(studyDO.getPlannedStartTime());
		studyDO.setState(studyDO.getState());
		studyDO.setEstimatedEndTime(studyDO.getEstimatedEndTime());
	}

}
