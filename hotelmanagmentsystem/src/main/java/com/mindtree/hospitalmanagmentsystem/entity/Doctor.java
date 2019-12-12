package com.mindtree.hospitalmanagmentsystem.entity;

import java.util.Comparator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Doctor {
	@Id
	public int doctorId;
	public String doctorName;
	public int experience;
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
