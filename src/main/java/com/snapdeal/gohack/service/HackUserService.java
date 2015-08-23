package com.snapdeal.gohack.service;

import com.snapdeal.gohack.model.AuthenticateResponse;
import com.snapdeal.gohack.model.HackUser;


public interface HackUserService {
	
	public String doUserRegistration(HackUser user);

	public String forgotPassword(String emailId);

	public AuthenticateResponse doUserAuthentication(HackUser user);
	

}
