package com.mindtree.hospitalmanagmentsystem.service.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.hospitalmanagmentsystem.entity.Doctor;
import com.mindtree.hospitalmanagmentsystem.entity.Patient;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;
import com.mindtree.hospitalmanagmentsystem.exception.util.ErrorConstants;
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
		
//		doctors=doctorrepo.findAll();
//		
//		int pcnt=0;
//		
//		int dcnt=0;
//		
//		 Patient spatient=null;
//		
//		for (Doctor doctor : doctors) {
//			
//			if(doctor.getDoctorName().compareTo(doctorName)==0)
//			{
//				dcnt++;
//				//System.out.println(doctor);
//				for (Patient patient : patientrepo.findAll()) {
//					if(patient.getPatientName().compareTo(patientName)==0)
//					{
//						pcnt++;
//						//System.out.println(patient);
//						patient.setDoctor(doctor);
//						patientrepo.save(patient);
//						spatient=patient;
//						break;
//						
//					}
//					
//				}
//			}
//			
//		}
//		
//		if(pcnt==0 && dcnt>=0)
//		{
//			try {
//			throw new PatientNotFoundException("patient not found");
//			}catch(PatientNotFoundException e)
//			{
//				throw new ServiceException(e.getMessage());
//			}
//		}
//		
//		
//		if(dcnt==0)
//		{
//			try {
//			throw new DoctorNotFoundException("doctor not found");
//		}catch(DoctorNotFoundException e)
//			{
//			 throw new ServiceException(e.getMessage());
//			}
//		}
		
		//creating a custom method in the repository so as get the doctor by doctorname
	java.util.Optional<Doctor> doctor=doctorrepo.getDoctorByDoctorName(doctorName);
	
	//System.out.println(doctor.get());
	//it will throw a service exception if the requested doctor is not found
	doctor.orElseThrow(()->new ServiceException(ErrorConstants.NOSUCHDOCTOR) );
	
	
	
	
	// for patient 
	//creating method in the patient repositoty so as to fetch the patient by his name
	java.util.Optional<Patient> patient =patientrepo.getPatientByPatientName(patientName);
	
	//the below statment will throw an exception stating the patient is not found 
	
	patient.orElseThrow(()->new ServiceException(ErrorConstants.NOSUCHPATIENT));
	
	patient.get().setDoctor(doctor.get());
	
	
	
		
		//return spatient;
		return patientrepo.save(patient.get());
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
		
		
		
	/*	doctors.forEach(d->d.getPatient().forEach(p->{
			sum=sum+p.getBillAmount();
		}));*/
		

		Comparator<Doctor> sortBySalary = new Comparator<Doctor>() {

			public int compare(Doctor arg0, Doctor arg1) {
     
				return arg0.salary - arg1.salary;
			}
		};
		
		
		
		Collections.sort(doctors,sortBySalary);
		
		//doctors.stream().map(doctorentity->convertEntityToDto(doctorentity));
	
		   return doctors;
	}

/*
	private Object convertEntityToDto(Doctor doctorentity) {
		Modlema
		return null;
	}*/


	public List<Doctor> getDoctorswithsomecriteria() throws ServiceException {
		
		List<Doctor> doctors=doctorrepo.findAll();
		
		List<Doctor> requiredDoctors=new ArrayList();
	/*	
		for (Doctor doctor : doctors) {
			if(doctor.getPatient().size()>3)
			{
				requiredDoctors.add(doctor);
			}
		}*/
		List<Doctor> requireddoctorslist;
		
			requireddoctorslist=doctors.stream().filter(d->d.getPatient().size()>4).collect(Collectors.toList());
			
			if(requireddoctorslist.isEmpty())
			{
				throw new ServiceException(ErrorConstants.NOSUCHDOCTOR);
			}
		
		
		System.out.println("required doctors list");
		System.out.println(requireddoctorslist);
		
		Comparator<Doctor> sortByExperience = new Comparator<Doctor>() {

			public int compare(Doctor arg0, Doctor arg1) {

				return arg0.experience - arg1.experience;
			}
		};
		
		//Collections.sort(requiredDoctors,sortByExperience);
		
		Collections.sort(requireddoctorslist,sortByExperience);
		
		return requireddoctorslist;
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
		bw.write(result);
		
		
		bw.close();
		fw.close();
		
	}
	
	public void writeIntoExcelSheet()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Doctor Details");
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("doctorId");
		headerRow.createCell(1).setCellValue("doctorName");
		headerRow.createCell(2).setCellValue("Experience");
		headerRow.createCell(3).setCellValue("Salary");
		
		int rowNum = 1;
		for (Doctor d : doctorrepo.findAll()) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(d.getDoctorId());
			row.createCell(1).setCellValue(d.getDoctorName());
			row.createCell(2).setCellValue(d.getExperience());
			row.createCell(3).setCellValue(d.getSalary());
		}
		  try
		  {
		   //Write the workbook in file system
		   FileOutputStream out = new FileOutputStream("D:\\DoctorDetails.xlsx");
		   workbook.write(out);
		   out.close();
		   System.out.println("Doctor Details.xlsx has been created successfully");
		  } 
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  }
		  finally {
		   try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  }
	}
	




}
