package com.mindtree.hospitalmanagmentsystem.dto;

import java.util.Set;

public class DoctorDto {

	public int doctorId;
	public String doctorName;
	public int experience;
	public int salary;

	Set<PatientDto> patients;

	public DoctorDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorDto(int doctorId, String doctorName, int experience, int salary, Set<PatientDto> patients) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.experience = experience;
		this.salary = salary;
		this.patients = patients;
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

	public Set<PatientDto> getPatients() {
		return patients;
	}

	public void setPatients(Set<PatientDto> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "DoctorDto [doctorId=" + doctorId + ", doctorName=" + doctorName + ", experience=" + experience
				+ ", salary=" + salary + ", patients=" + patients + "]";
	}
	
	

}
