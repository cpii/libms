package com.hurontg.libms.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class AuthenticationFailureHandlerImpl extends
		SimpleUrlAuthenticationFailureHandler {

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(AuthenticationFailureHandlerImpl.class.getName());

	public AuthenticationFailureHandlerImpl() {
		super("/login_authfailure");
	}

	public AuthenticationFailureHandlerImpl(String defaultFailureUrl) {
		super(defaultFailureUrl);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		Authentication auth = exception.getAuthentication();
		if (auth != null) {
			logger.info("username: " + auth.getName());
		}
		super.onAuthenticationFailure(request, response, exception);
	}

}
