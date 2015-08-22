package com.hurontg.libms.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.UserSession;
import com.hurontg.libms.service.UserSessionService;

public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(LogoutSuccessHandlerImpl.class.getName());
	@Inject
	private UserSessionService userSessionSvc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.logout.
	 * SimpleUrlLogoutSuccessHandler
	 * #onLogoutSuccess(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.entry();
		if (authentication != null) {
			AuthUser user = (AuthUser) authentication.getPrincipal();
			if (user != null) {
				UserSession us = new UserSession(user.getUserSessionId());
				userSessionSvc.saveLogout(us);
			}
		}
		logger.exit();

		response.sendRedirect(request.getContextPath() + "/logout");

	}

}
