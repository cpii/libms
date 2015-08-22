package com.hurontg.libms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class AuthenticationSuccessHandlerImpl extends
		SimpleUrlAuthenticationSuccessHandler {

	/**
	 * 
	 */
	public AuthenticationSuccessHandlerImpl() {
		super("/newlogin");
	}

	/**
	 * 
	 * @param defaultTargetUrl
	 */
	public AuthenticationSuccessHandlerImpl(String defaultTargetUrl) {
		super(defaultTargetUrl);
	}

	/**
	 * 
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		super.onAuthenticationSuccess(request, response, authentication);
	}
}
