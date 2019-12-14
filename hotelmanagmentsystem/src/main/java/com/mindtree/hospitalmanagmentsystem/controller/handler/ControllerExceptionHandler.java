package com.mindtree.hospitalmanagmentsystem.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.hospitalmanagmentsystem.controller.HospitalManagmentController;
import com.mindtree.hospitalmanagmentsystem.exception.ServiceException;

@RestControllerAdvice( assignableTypes= { HospitalManagmentController.class})
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<String> serviceLayerException(Exception e,Throwable cause)
	{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		
	}
}
