package com.mindtree.hospitalmanagmentsystem.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	//one way of doing in controller layer
	
	/*@GetMapping(path="/assignDoctor/{doctorName}/{patientName}")
	public Patient assignDoctorToPatient(@PathVariable("doctorName") String doctorName,@PathVariable("patientName") String patientName) throws ServiceException
	{
	 Patient p= new Patient();
		p= hospitalservice.assignDoctorToPatient(doctorName,patientName);
    return p;
		 
	}*/
	
	
	//other way of doing using response entity
	
	@GetMapping(path="/assignDoctor/{doctorName}/{patientName}")
	public ResponseEntity<Map<String,Object>> assignDoctorToPatient(@PathVariable("doctorName") String doctorName,@PathVariable("patientName") String patientName) throws ServiceException
	{
		Map<String,Object> response =new LinkedHashMap<String, Object>();
		response.put("header", "kalinga hospitals");
		response.put("Error",false);
		response.put("body",hospitalservice.assignDoctorToPatient(doctorName,patientName));
	
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	
	
	@GetMapping(path="/getDoctors")
	public List<Doctor> getDoctors()
	{
		
		return 	hospitalservice.getDoctors();
	
		
	
	}
	
	@GetMapping(path="/getDoctors2")
	public List<Doctor> getDoctorswithsomecriteria() throws ServiceException
	{
		List<Doctor> doctors;
		doctors=hospitalservice.getDoctorswithsomecriteria();
	
		return doctors;	
	
	}
	
	@GetMapping(path="/writeintotextfile")
	public String writeIntoTextFile() throws IOException
	{
		
		hospitalservice.writeIntoTextFile();
		
		return "check the text file";
		
	}
	
	@GetMapping(path="/writeIntoExcel")
	public String writeintoExcelSheet() throws IOException
	{
		hospitalservice.writeIntoExcelSheet();
		return "check the excelsheet";
	}
			

	


}
