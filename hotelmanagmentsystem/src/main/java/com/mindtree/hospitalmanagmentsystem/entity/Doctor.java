package com.mindtree.hospitalmanagmentsystem.entity;

import java.util.Comparator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Doctor {
	@Id
	public int doctorId;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctorId;
		result = prime * result + ((doctorName == null) ? 0 : doctorName.hashCode());
		result = prime * result + experience;
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result + salary;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (doctorId != other.doctorId)
			return false;
		if (doctorName == null) {
			if (other.doctorName != null)
				return false;
		} else if (!doctorName.equals(other.doctorName))
			return false;
		if (experience != other.experience)
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}

	public String doctorName;
	public int experience;
	@Column(nullable=true)
	public int salary;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	Set<Patient> patient;

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int doctorId, String doctorName, int experience, int salary, Set<Patient> patient) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.experience = experience;
		this.salary = salary;
		this.patient = patient;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@JsonIgnoreProperties("doctor")
	public Set<Patient> getPatient() {
		return patient;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", experience=" + experience
				+ ", salary=" + salary + ", patient=" + patient + "]";
	}

	public void setPatient(Set<Patient> patient) {
		this.patient = patient;
	}


}
