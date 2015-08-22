package com.hurontg.libms.security;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DelegatingHttpSessionEventListenerProxy implements
		HttpSessionListener {

	/**
	 * Delegates sessionCreated Event to the Spring-bean
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());

		HttpSessionListener target = context.getBean(
				"httpSessionEventListener", HttpSessionListener.class);
		target.sessionCreated(se);
	}

	/**
	 * Delegates sessionDestroyed Event to the Spring-bean
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(se.getSession().getServletContext());

		HttpSessionListener target = context.getBean(
				"httpSessionEventListener", HttpSessionListener.class);
		target.sessionDestroyed(se);
	}
}
