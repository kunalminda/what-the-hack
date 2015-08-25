package com.snapdeal.gohack.model;

public class Status {
	
	public boolean Status;
	
	public Status() {
		Status=Boolean.TRUE;
	}
	
	
	
	public Status(boolean status, String message) {
		super();
		Status = status;
		this.message = message;
	}



	public String message;

	public boolean isStatus() {
		return Status;
	}

	public void setStatus(boolean status) {
		Status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
