/**
 * 
 */
package com.demo.domainobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/**
 * @author neelam
 *
 */
@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Doctor", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class DoctorDO implements Serializable {
	private static final long serialVersionUID = -2949357173128605764L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	@Column(name = "name", unique = true, nullable = false, length = 100)
	private String name;

	// getters and setters
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
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

}
