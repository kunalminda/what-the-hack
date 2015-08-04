package com.snapdeal.gohack.core;



public interface HackUserService {
	
	public String doUserRegistration(HackUser user);

	public String forgotPassword(String emailId);

	public AuthenticateResponse doUserAuthentication(HackUser user);
	

}
