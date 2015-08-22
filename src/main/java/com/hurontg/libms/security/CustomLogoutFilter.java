package com.hurontg.libms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomLogoutFilter extends OncePerRequestFilter {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(CustomLogoutFilter.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		
		logger.debug("========================================================================================");
		logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Custom Logout Filter $$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		logger.debug("========================================================================================");

		chain.doFilter(req, res);
	}

}
