package com.mindtree.hospitalmanagmentsystem.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.hospitalmanagmentsystem.entity.Doctor;
import com.mindtree.hospitalmanagmentsystem.entity.Patient;
import com.mindtree.hospitalmanagmentsystem.exception.DoctorNotFoundException;
import com.mindtree.hospitalmanagmentsystem.exception.PatientNotFoundException;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;
import com.mindtree.hospitalmanagmentsystem.repository.RepositoryDoctor;
import com.mindtree.hospitalmanagmentsystem.repository.RepositoryPatient;
import com.mindtree.hospitalmanagmentsystem.service.HospitalManagmentService;
@Service
public class HospitalManagmentImpl implements HospitalManagmentService {
	@Autowired
	RepositoryDoctor doctorrepo;
	
	@Autowired
	RepositoryPatient patientrepo;
	

	public Patient assignDoctorToPatient(String doctorName, String patientName) throws ServiceException {
		// TODO Auto-generated method stub
		
		List<Doctor> doctors=new ArrayList<Doctor>();
		
		doctors=doctorrepo.findAll();
		
		int pcnt=0;
		
		int dcnt=0;
		
		 Patient spatient=null;
		
		for (Doctor doctor : doctors) {
			
			if(doctor.getDoctorName().compareTo(doctorName)==0)
			{
				dcnt++;
				//System.out.println(doctor);
				for (Patient patient : patientrepo.findAll()) {
					if(patient.getPatientName().compareTo(patientName)==0)
					{
						pcnt++;
						//System.out.println(patient);
						patient.setDoctor(doctor);
						patientrepo.save(patient);
						spatient=patient;
						break;
						
					}
					
				}
			}
			
		}
		
		if(pcnt==0 && dcnt>=0)
		{
			try {
			throw new PatientNotFoundException("patient not found");
			}catch(PatientNotFoundException e)
			{
				throw new ServiceException(e.getMessage());
			}
		}
		
		
		if(dcnt==0)
		{
			try {
			throw new DoctorNotFoundException("doctor not found");
		}catch(DoctorNotFoundException e)
			{
			 throw new ServiceException(e.getMessage());
			}
		}
		
		
		return spatient;
	}


	public List<Doctor> getDoctors() {
		
		List<Doctor> doctors=doctorrepo.findAll();
		int sum=0;
		int cnt=0;
		for (Doctor doctor : doctors) {
			for (Patient patient : doctor.getPatient()) {
				sum=sum+patient.getBillAmount();
			}
			
			doctor.setSalary(sum);
			
			doctorrepo.save(doctor);
			
			sum=0;
		}
		

		Comparator<Doctor> sortBySalary = new Comparator<Doctor>() {

			public int compare(Doctor arg0, Doctor arg1) {

				return arg0.salary - arg1.salary;
			}
		};
		
		
		
		Collections.sort(doctors,sortBySalary);
	
		   return doctors;
	}


	public List<Doctor> getDoctorswithsomecriteria() {
		
		List<Doctor> doctors=doctorrepo.findAll();
		
		List<Doctor> requiredDoctors=new ArrayList();
		
		for (Doctor doctor : doctors) {
			if(doctor.getPatient().size()>3)
			{
				requiredDoctors.add(doctor);
			}
		}
		
		Comparator<Doctor> sortByExperience = new Comparator<Doctor>() {

			public int compare(Doctor arg0, Doctor arg1) {

				return arg0.experience - arg1.experience;
			}
		};
		
		Collections.sort(requiredDoctors,sortByExperience);
		
		return requiredDoctors;
	}


	public void writeIntoTextFile() throws IOException {
		List<Doctor>doctors =new ArrayList<Doctor>();
		
		doctors=doctorrepo.findAll();
		//System.out.println(doctors);
		
		
		String result="";
		
		for (Doctor doctor : doctors) {
			result=result+doctor.toString();
			
		}
		System.out.println(result);
		
		FileWriter fw=new FileWriter("C:\\Users\\M1056104\\Desktop\\output.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		//fw.write("hi i am inside the output file");
		fw.write(result);
		
		
		bw.close();
		fw.close();
		
	}
	




}
