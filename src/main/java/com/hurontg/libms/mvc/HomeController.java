package com.hurontg.libms.mvc;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.util.Utils;
import com.hurontg.libms.domain.Authority;
import com.hurontg.libms.domain.BorrowedItem;
import com.hurontg.libms.domain.Car;
import com.hurontg.libms.service.BookService;
import com.hurontg.libms.service.CarService;

@Controller
public class HomeController extends BaseController {

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(HomeController.class
			.getName());

	@Inject
	private CarService carSvc;
	@Inject
	private BookService bookSvc;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model) throws Exception {
		logger.entry();

		String viewName = "forward:/" + getHomePageByRole();

		// logger.exit(viewName);

		return viewName;
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

	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String getAdminHomePage(Model model) throws Exception {
		logger.entry();

		String viewName = "admin-home";

		logger.exit(viewName);

		return viewName;
	}

	@RequestMapping(value = "/staffHome", method = RequestMethod.GET)
	public String getStaffHomePage(Model model) throws Exception {
		logger.entry();

		String viewName = "staff-home";

		logger.exit(viewName);

		return viewName;
	}

	@RequestMapping(value = "/memberHome", method = RequestMethod.GET)
	public String getMemberHomePage(Model model) throws Exception {
		logger.entry();

		String viewName = "member-home";
		List<BorrowedItem> items = bookSvc.getBorrowedItemsByUser();
		model.addAttribute("items", items);
		logger.exit(viewName);

		return viewName;
	}

	@RequestMapping(value = { "/cars" }, method = RequestMethod.GET)
	public String showCarsMainPage(Model model) throws Exception {
		@SuppressWarnings("unused")
		List<Car> cars = carSvc.findAllCars();
		return "cars.jsp";
	}

}
