package com.hurontg.libms.mvc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.util.Utils;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Authority;
import com.hurontg.libms.domain.UserSession;
import com.hurontg.libms.service.UserSessionService;

@Controller
public class UserSessionController extends BaseController {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(UserSessionController.class.getName());

	/**
	 * 
	 */
	private UserSessionService usSvc;

	@Inject
	public void setUssvc(UserSessionService usSvc) {
		this.usSvc = usSvc;
	}

	/**
	 * Take user to Login.jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLoginPage(Model model) {
		logger.entry();

		logger.exit("login");

		return "login";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String displayLogutPage(Model model) {
		logger.entry();

		logger.exit("logout");

		return "logout";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_sessionexpired", method = RequestMethod.GET)
	public String displayLoginPageForExpiredSession(Model model) {
		logger.entry("login_sessionexpired");

		// This attribute will be used by the JSP to display info to the user.
		model.addAttribute("sessionexpired", "true");

		logger.exit("login");

		return "login";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login_authfailure", method = RequestMethod.GET)
	public String displayLoginPageForAuthenticationFailure(Model model) {
		logger.entry();

		// This attribute will be used by the JSP to display info to the user.
		model.addAttribute("login_error", "true");

		logger.exit("login");

		return "login";
	}

	/**
	 * A new user-login, record login time and dispatch to the default 'home'
	 * page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newlogin")
	public String dispatchTodefaultEntryPoint() {
		logger.entry();

		saveUserLoginInformation();

		// String defaultHome = "redirect:/home";

		// if (Utils.isUserInRole(getInternalUserRoles())) {
		//
		// } else {
		// throw new AccessDeniedException("User Role not found");
		// }

		String homePage = "forward:/" + getHomePageByRole();

		logger.exit();

		return homePage;
	}

	private String getHomePageByRole() {
		if (Utils.isUserInRole(Authority.ROLE_ADMINISTRATOR)) {
			return "adminHome";
		} else if (Utils.isUserInRole(Authority.ROLE_STAFF)) {
			return "staffHome";
		} else if (Utils.isUserInRole(Authority.ROLE_MEMBER)) {
			return "memberHome";
		} else {
			throw new AccessDeniedException("User Role not found");
		}
	}

	/**
	 * 
	 */
	private void saveUserLoginInformation() {
		logger.entry();

		AuthUser aUser = Utils.getAuthUser();

		UserSession us = new UserSession();

		us.setLoginDate(new Date());
		us.setIp(Utils.getAuthenticationDetails().getRemoteAddress());
		us.setUsername(aUser.getUsername());

		usSvc.saveLogin(us);

		aUser.setUserSessionId(us.getId());

		logger.exit();
	}

	/**
	 * 
	 * @param model
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/accessDenied")
	public String accessDenied(ModelMap model, HttpServletRequest request) {
		logger.entry();

		AccessDeniedException ex = (AccessDeniedException) request
				.getAttribute(WebAttributes.ACCESS_DENIED_403);

		if (ex != null) {
			StringWriter sw = new StringWriter();
			model.addAttribute("errorDetails", ex.getMessage());
			ex.printStackTrace(new PrintWriter(sw));
			model.addAttribute("errorTrace", sw.toString());
		} else {
			model.addAttribute("errorDetails",
					"Unauthorized operation. The occurance will be reported.");
			model.addAttribute("errorTrace", "");
		}

		logger.exit();

		return "accessDenied";
	}

	/**
	 * 
	 * @return
	 */
	protected List<String> getInternalUserRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add(Authority.ROLE_USER);
		return roles;
	}
}
