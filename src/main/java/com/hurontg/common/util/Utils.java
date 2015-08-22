package com.hurontg.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.hurontg.libms.domain.AuthUser;

public class Utils {

	/**
	 * 
	 * @return
	 */
	public static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	/**
	 * 
	 * @return
	 */
	public static WebAuthenticationDetails getAuthenticationDetails() {
		return (WebAuthenticationDetails) SecurityContextHolder.getContext()
				.getAuthentication().getDetails();
	}

	/**
	 * 
	 * @return
	 */
	public static AuthUser getAuthUser() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		AuthUser aUser = (AuthUser) principal;

		return aUser;
	}

	/**
	 * 
	 * @return
	 */
	public static String getUsername() {
		return ((UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
	}

	/**
	 * returns true if user has the authority "role"
	 */
	public static boolean isUserInRole(String role) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		for (GrantedAuthority ga : auth.getAuthorities()) {
			if (ga.getAuthority().equalsIgnoreCase(role)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * returns true if user has the authority "role"
	 */
	public static boolean isUserInRole(List<String> roles) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		for (String role : roles) {
			for (GrantedAuthority ga : auth.getAuthorities()) {
				if (ga.getAuthority().equalsIgnoreCase(role)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns a Date object for Dec 31, 9999
	 * 
	 * @return
	 */
	public static Date getMaxDate() {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(Calendar.YEAR, 9999);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return cal.getTime();
	}
}
