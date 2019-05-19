/**
 * 
 */
package com.demo.domainobject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author neelam
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Study", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class StudyDO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5341979009888396392L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	private Long id;


	@NotEmpty(message = "Please provide a description")
	@Column(name = "description")
	private String description;

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	private State state;

	@NotNull(message = "Please provide a planned start time")
	@Column(name = "plannedStartTime")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date plannedStartTime;

	@Column(name = "estimatedEndTime")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date estimatedEndTime;

	@OneToOne(targetEntity = PatientDO.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private PatientDO patientDetails;
	
	
	
	public static enum State {

		PLANNED("planned"),
		INPROGRESS("inprogress"),
	    FINISHED("finished");

	    private final String displayName;

	    State(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }
	}
	
	public StudyDO() {
		super();
	}

	public StudyDO(PatientDO patientDetails) {
		super();
		this.patientDetails = patientDetails;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * @return the plannedStartTime
	 */
	public Date getPlannedStartTime() {
		return plannedStartTime;
	}

	/**
	 * @param plannedStartTime
	 *            the plannedStartTime to set
	 */
	public void setPlannedStartTime(Date plannedStartTime) {
		this.plannedStartTime = plannedStartTime;
	}

	/**
	 * @return the estimatedStartTime
	 */
	public Date getEstimatedEndTime() {
		return estimatedEndTime;
	}

	/**
	 * @param estimatedStartTime
	 *            the estimatedStartTime to set
	 */
	public void setEstimatedEndTime(Date estimatedEndTime) {
		this.estimatedEndTime = estimatedEndTime;
	}

	/**
	 * @return the patientDetails
	 */
	public PatientDO getPatientDetails() {
		return patientDetails;
	}

	/**
	 * @param patientDetails
	 *            the patientDetails to set
	 */
	public void setPatientDetails(PatientDO patientDetails) {
		this.patientDetails = patientDetails;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	

}
