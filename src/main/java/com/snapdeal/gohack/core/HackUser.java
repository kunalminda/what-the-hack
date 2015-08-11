package com.snapdeal.gohack.core;


public class HackUser {
	
	
	public HackUser() {
		setEnabled(Boolean.TRUE.booleanValue());
	}
	
	private String fname;
	
	private String lname;
	
	
	private boolean isEnabled;
	


	
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	private int employeeId;
	
	private String sme;
	
	
	private String otherExpertise;
	
	private String designation;
	
	private String team;
	
	private String email;
	
	private String password;
	
	
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getSme() {
		return sme;
	}

	public void setSme(String sme) {
		this.sme = sme;
	}

	public String getOtherExpertise() {
		return otherExpertise;
	}

	public void setOtherExpertise(String otherExpertise) {
		this.otherExpertise = otherExpertise;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "HackUser [fname=" + fname + ", lname=" + lname
				+ ", employeeId=" + employeeId + ", sme=" + sme
				+ ", otherExpertise=" + otherExpertise + ", designation="
				+ designation + ", team=" + team + ", email=" + email
				+ ", password=" + password + "]";
	}


}
