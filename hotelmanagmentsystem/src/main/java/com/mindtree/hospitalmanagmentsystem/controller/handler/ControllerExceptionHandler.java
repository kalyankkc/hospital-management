package com.mindtree.hospitalmanagmentsystem.controller.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.hospitalmanagmentsystem.controller.HospitalManagmentController;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;

@RestControllerAdvice( assignableTypes= { HospitalManagmentController.class})
public class ControllerExceptionHandler {
	
	//one method to handle the service exceptions 
/*	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<String> serviceLayerException(Exception e,Throwable cause)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}*/
	
	
	//other way of handling the service exceptions
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Map<String,Object>> serviceExceptionHandler(Exception e,Throwable cause)
	{
		Map<String,Object> response =new LinkedHashMap<String, Object>();
		
		response.put("header","kalinga hospital");
		response.put("Error", true);
		response.put("body", e.getMessage());
		response.put("Httpstatus", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	}
}
