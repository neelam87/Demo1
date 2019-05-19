/**
 * 
 */
package com.demo.domainobject;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author neelam
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Patient", uniqueConstraints = { @UniqueConstraint(columnNames = { "id", "name" }) })
public class PatientDO implements Serializable {

	private static final long serialVersionUID = -2430629828694923045L;

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Please provide a name")
	@Column(name = "name")
	private String name;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "dateOfBirth")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dateOfBirth;

	
	private RoomDO roomDetails;
	private DoctorDO doctorDetails;

	
	public PatientDO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientDO(DoctorDO doctorDetails, RoomDO roomDetails) {
		super();
		this.doctorDetails = doctorDetails;
		this.roomDetails = roomDetails;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the roomDetails
	 */
	public RoomDO getRoomDetails() {
		return roomDetails;
	}

	/**
	 * @param roomDetails
	 *            the roomDetails to set
	 */
	public void setRoomDetails(RoomDO roomDetails) {
		this.roomDetails = roomDetails;
	}

	public DoctorDO getDoctorDetails() {
		return doctorDetails;
	}

	public void setDoctorDetails(DoctorDO doctorDetails) {
		this.doctorDetails = doctorDetails;
	}

	

}
