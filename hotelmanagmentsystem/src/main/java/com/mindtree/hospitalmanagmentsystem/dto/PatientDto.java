package com.mindtree.hospitalmanagmentsystem.dto;

public class PatientDto {

	public int patientId;
	public String patientName;
	public int billAmount;

	private DoctorDto doctor;

	public PatientDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientDto(int patientId, String patientName, int billAmount, DoctorDto doctor) {
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

	public DoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "PatientDto [patientId=" + patientId + ", patientName=" + patientName + ", billAmount=" + billAmount
				+ ", doctor=" + doctor + "]";
	}

	

}
