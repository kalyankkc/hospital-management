package com.mindtree.hospitalmanagmentsystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.hospitalmanagmentsystem.entity.Doctor;
import com.mindtree.hospitalmanagmentsystem.entity.Patient;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;
import com.mindtree.hospitalmanagmentsystem.service.HospitalManagmentService;

@RestController
public class HospitalManagmentController {
	
	@Autowired
	HospitalManagmentService hospitalservice;
	
	@GetMapping(path="/assignDoctor/{doctorName}/{patientName}")
	public Patient assignDoctorToPatient(@PathVariable("doctorName") String doctorName,@PathVariable("patientName") String patientName) throws ServiceException
	{
	 Patient p= new Patient();
		p= hospitalservice.assignDoctorToPatient(doctorName,patientName);
    return p;
		 
	}
	
	@GetMapping(path="/getDoctors")
	public List<Doctor> getDoctors()
	{
		
		return 	hospitalservice.getDoctors();
	
		
	
	}
	
	@GetMapping(path="/getDoctors2")
	public List<Doctor> getDoctorswithsomecriteria()
	{
		
		return 	hospitalservice.getDoctorswithsomecriteria();
	
		
	
	}
	
	@GetMapping(path="/writeintotextfile")
	public String writeIntoTextFile() throws IOException
	{
		
		hospitalservice.writeIntoTextFile();
		
		return "check the text file";
		
	}
	


}
