package com.mindtree.hospitalmanagmentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
public class Patient {
	@Id
	public int patientId;
	public String patientName;
	public int billAmount;
	
	@ManyToOne
	Doctor doctor;
	
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Patient(int patientId, String patientName, int billAmount, Doctor doctor) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.billAmount = billAmount;
		this.doctor = doctor;
	}


	public int getPatientId() {
		return patientId;
	}


	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public int getBillAmount() {
		return billAmount;
	}


	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}

	public Doctor getDoctor() {
		return doctor;
	}

    @JsonIgnoreProperties("patient")
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
	


