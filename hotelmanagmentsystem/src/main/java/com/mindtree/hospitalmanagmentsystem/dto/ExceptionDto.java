package com.mindtree.hospitalmanagmentsystem.dto;

public class ExceptionDto {
	
	
	private String message;

	public ExceptionDto(String message) {
		super();
		this.setMessage(message);
	}

	public ExceptionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
