package com.mindtree.hospitalmanagmentsystem.service;

import java.io.IOException;
import java.util.List;

import com.mindtree.hospitalmanagmentsystem.entity.Doctor;
import com.mindtree.hospitalmanagmentsystem.entity.Patient;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;

public interface HospitalManagmentService {

	public Patient assignDoctorToPatient(String doctorName, String patientName) throws ServiceException;

	public List<Doctor> getDoctors();

	public List<Doctor> getDoctorswithsomecriteria();

	public void writeIntoTextFile() throws IOException;

}
