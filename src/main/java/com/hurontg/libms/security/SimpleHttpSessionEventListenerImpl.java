package com.hurontg.libms.security;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.UserSession;
import com.hurontg.libms.service.UserSessionService;

public class SimpleHttpSessionEventListenerImpl implements HttpSessionListener {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(SimpleHttpSessionEventListenerImpl.class.getName());

	/**
	 * 
	 */
	private UserSessionService uss;

	@Inject
	public void setUss(UserSessionService uss) {
		this.uss = uss;
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.entry(se);
		
		Object o = se
				.getSession()
				.getAttribute(
						HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if (o != null) {
			Authentication auth = ((SecurityContext) o).getAuthentication();

			UserSession us = new UserSession();
			us.setId(((AuthUser) auth.getPrincipal()).getUserSessionId());
			us.setLogoutDate(new Date());

			uss.saveLogout(us);
		}
		
		logger.exit();
	}
}
