package com.snapdeal.gohack.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;



@Component
public class AuthenticationSuccessHandler extends
SavedRequestAwareAuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
					throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String ctoken = (String) request.getSession().getAttribute(WebConstants.CSRF_TOKEN);
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		System.out.println(defaultSavedRequest);
		if( defaultSavedRequest !=null ) {
			//defaultSavedRequest.getRedirectUrl();
//			String requestUrl = defaultSavedRequest.getRequestURL() + "?" + defaultSavedRequest.getQueryString();
//			requestUrl = UrlTool.addParamToURL(requestUrl, WebConstants.CSRF_TOKEN, ctoken, true);
			getRedirectStrategy().sendRedirect(request, response, defaultSavedRequest.getRedirectUrl());
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}
