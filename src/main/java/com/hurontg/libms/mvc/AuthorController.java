package com.hurontg.libms.mvc;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.util.Constants;
import com.hurontg.libms.domain.Author;
import com.hurontg.libms.service.AuthorService;

@Controller()
@RequestMapping(value = "/authors")
public class AuthorController extends BaseController {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(AuthorController.class
			.getName());

	@Inject
	private AuthorService authorSvc;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showMainEquipmentPage(Model model) throws Exception {
		logger.entry();

		String viewName = "author-main";

		try {
			Author author = new Author();
			model.addAttribute("author", author);
		} catch (Throwable t) {
			logger.catching(t);
		}

		logger.exit(viewName);

		return viewName;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAuthors(Model model, HttpServletResponse resp)
			throws Exception {
		logger.entry();
		String tmpl = "author/authorTable.jsp";

		try {
			List<Author> authors = authorSvc.findAll();
			model.addAttribute("authors", authors);
		} catch (ServiceException ems) {
			logger.catching(ems);

			resp.setStatus(500);
			model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
			tmpl = Constants.INLINE_ERRORS_JSP;
		}

		logger.exit();

		return tmpl;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> postAuthor(
			@ModelAttribute("author") Author author, Model model,
			HttpServletResponse resp) {
		logger.entry();
		ResponseEntity<String> re = new ResponseEntity<String>(
				"Author created successfully", HttpStatus.OK);

		try {
			authorSvc.create(author);
		} catch (ServiceException ems) {
			logger.catching(ems);

			resp.setStatus(500);
			re = new ResponseEntity<String>(ems.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.exit();

		return re;
	}

	@RequestMapping(value = "/{authorId}/edit", method = RequestMethod.GET)
	public String getAuthor(@PathVariable("authorId") Long authorId,
			Model model, HttpServletResponse resp) throws Exception {
		logger.entry();
		String tmpl = "author/author.jsp";
		try {
			Author author = authorSvc.findById(authorId);
			model.addAttribute("author", author);
		} catch (ServiceException ems) {
			logger.catching(ems);

			resp.setStatus(500);
			model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
			tmpl = Constants.INLINE_ERRORS_JSP;
		}

		logger.exit();

		return tmpl;
	}

	@RequestMapping(value = "/{authorId}", method = RequestMethod.PUT)
	public ResponseEntity<String> putAuthor(
			@ModelAttribute("author") Author author, Model model,
			HttpServletResponse resp) throws Exception {
		logger.entry();
		ResponseEntity<String> re = new ResponseEntity<String>(
				"Author Edited successfully", HttpStatus.OK);
		try {
			authorSvc.update(author);
		} catch (ServiceException ems) {
			logger.catching(ems);
			re = new ResponseEntity<String>(ems.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		logger.exit();
		return re;
	}

	// @RequestMapping(value = "/datatable.json", method = RequestMethod.GET)
	// public @ResponseBody
	// EquipmentDatatable getEquipmentsDatatable(Model model,
	// HttpServletResponse resp) throws Exception {
	// logger.entry();
	// EquipmentDatatable dt = new EquipmentDatatable();
	// try {
	// List<Equipment> eqs = equipSvc.loadEquipments();
	// dt.transform(eqs);
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	// resp.setStatus(500);
	// }
	//
	// logger.exit();
	//
	// return dt;
	// }
	//
	// /**
	// *
	// * @param model
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/searchbyequipmentnumber", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// List<EquipmentDTO> searchByEquipmentNumber(
	// @RequestParam("term") String term, Model model,
	// HttpServletResponse resp) {
	// logger.entry();
	//
	// List<EquipmentDTO> equipmentDto = new ArrayList<EquipmentDTO>();
	//
	// try {
	// List<Equipment> equipment = equipSvc.searchByEquipmentNumber(term);
	//
	// for (Equipment e : equipment) {
	// EquipmentDTO dto = e.extract();
	// dto.setLabel(dto.getEquipmentNumber());
	// equipmentDto.add(dto);
	// }
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// }
	//
	// logger.exit();
	//
	// return equipmentDto;
	// }
	//
	// /**
	// *
	// * @param term
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/searchbyequipmentname", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// List<EquipmentDTO> searchByEquipmentName(@RequestParam("term") String
	// term,
	// Model model, HttpServletResponse resp) {
	// logger.entry();
	//
	// List<EquipmentDTO> equipmentDto = new ArrayList<EquipmentDTO>();
	//
	// try {
	// List<Equipment> equipment = equipSvc.searchByEquipmentName(term);
	//
	// for (Equipment e : equipment) {
	// EquipmentDTO dto = e.extract();
	// dto.setLabel(dto.getEquipmentName());
	// equipmentDto.add(dto);
	// }
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// }
	//
	// logger.exit();
	//
	// return equipmentDto;
	// }
	//
	// /**
	// *
	// * @param term
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/searchbyequipmentnameornumber", method =
	// RequestMethod.GET)
	// public @ResponseBody
	// List<EquipmentDTO> searchByEquipmentNameOrNumber(
	// @RequestParam("term") String term, Model model,
	// HttpServletResponse resp) {
	// logger.entry();
	//
	// List<EquipmentDTO> equipmentDto = new ArrayList<EquipmentDTO>();
	//
	// try {
	// List<Equipment> equipment = equipSvc
	// .searchByEquipmentNameOrNumber(term);
	//
	// for (Equipment e : equipment) {
	// EquipmentDTO dto = e.extract();
	// dto.setLabel(dto.getEquipmentName() + " ["
	// + dto.getEquipmentNumber() + "]");
	// equipmentDto.add(dto);
	// }
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// }
	//
	// logger.exit();
	//
	// return equipmentDto;
	// }
	//
	// /**
	// *
	// * @param equipId
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/new", method = RequestMethod.GET)
	// public String getNewEquipmentForm(Model model, HttpServletResponse resp)
	// {
	// logger.entry();
	// String tmpl = "equipment/Equipment.jsp";
	//
	// try {
	// model.addAttribute("equip", new Equipment(-1L));
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// tmpl = Constants.INLINE_ERRORS_JSP;
	// }
	//
	// logger.exit(tmpl);
	//
	// return tmpl;
	// }
	//
	// /**
	// *
	// * @param equipId
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/{equipId}/edit", method = RequestMethod.GET)
	// public String getEquipment(@PathVariable("equipId") Long equipId,
	// Model model, HttpServletResponse resp) {
	// logger.entry();
	// String tmpl = "equipment/Equipment.jsp";
	//
	// try {
	// Equipment equip = equipSvc.findByEquipmentId(equipId);
	// model.addAttribute("equip", equip);
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// tmpl = Constants.INLINE_ERRORS_JSP;
	// }
	//
	// logger.exit(tmpl);
	//
	// return tmpl;
	// }
	//
	// /**
	// *
	// * @param equip
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/{equipId}", method = RequestMethod.PUT)
	// public String putEquipment(@PathVariable("equipId") Long equipId,
	// @ModelAttribute("equip") Equipment equip, Model model,
	// HttpServletResponse resp) {
	// logger.entry();
	// String tmpl = Constants.INLINE_HIGHLIGHT_JSP;
	//
	// try {
	// equipSvc.updateEquipment(equip);
	// model.addAttribute("message", "Equipment updated successfully.");
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// tmpl = Constants.INLINE_ERRORS_JSP;
	// }
	//
	// logger.exit(tmpl);
	//
	// return tmpl;
	// }
	//
	// /**
	// * Returns a single equipment row which can be plucked in equipment
	// table's
	// * tr
	// *
	// * @param woId
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(value = "/{equipId}/html", method = RequestMethod.GET)
	// public String getEquipmentInHTMLTableRowFormat(
	// @PathVariable("equipId") Long equipId, Model model,
	// HttpServletResponse resp) {
	// logger.entry();
	// String tmpl = "equipment/EquipmentSingleTableRow.jsp";
	//
	// try {
	// Equipment equip = equipSvc.findByEquipmentId(equipId);
	// model.addAttribute("equip", equip);
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	// resp.setStatus(500);
	// }
	//
	// logger.exit(tmpl);
	//
	// return tmpl;
	// }
	//
	// /**
	// *
	// * @param wo
	// * @param model
	// * @param resp
	// * @return
	// */
	// @RequestMapping(method = RequestMethod.POST)
	// public String postEquipment(@ModelAttribute("equip") Equipment equip,
	// Model model, HttpServletResponse resp) {
	// logger.entry();
	// String tmpl = Constants.INLINE_HIGHLIGHT_JSP;
	//
	// try {
	// equip.setId(null); // We set it to -1 reset to null
	// equipSvc.createEquipment(equip);
	// model.addAttribute("message", "Equipment created successfully.");
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// tmpl = Constants.INLINE_ERRORS_JSP;
	// }
	//
	// logger.exit(tmpl);
	//
	// return tmpl;
	// }
	//
	// /**
	// * Return a form to allow user to enter search criteria
	// *
	// * @param model
	// * @return
	// */
	// @RequestMapping(value = "/searchform", method = RequestMethod.GET)
	// public String getEquipmentSearchForm(Model model) {
	// logger.entry();
	//
	// try {
	// model.addAttribute("equip", new Equipment());
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	// }
	//
	// logger.exit();
	//
	// return "equipment/EquipmentSearchForm.jsp";
	// }
	//
	// /**
	// *
	// * @param equip
	// * @param resp
	// * @param model
	// * @return
	// */
	// @RequestMapping(value = "/search", method = RequestMethod.POST)
	// public String postSearchEquipmentForm(
	// @ModelAttribute("equip") Equipment equip, HttpServletRequest req,
	// HttpServletResponse resp, Model model) {
	// logger.entry(equip);
	//
	// String tmpl = Constants.INLINE_HIGHLIGHT_JSP;
	// try {
	// // Is this a new search?
	// if (equip.getRowCount() == -1) {
	// equip.setRowCount(equipSvc.getSearchResultCount(equip));
	// }
	//
	// // perform search
	// String awaitingParts = req.getParameter("awaiting-parts");
	// if (awaitingParts == null) {
	// awaitingParts = "off";
	// }
	// List<Equipment> eqList = equipSvc.search(equip,
	// awaitingParts.equalsIgnoreCase("on"));
	// model.addAttribute("eqs", eqList);
	//
	// // Set Prev/Next buttons state
	// String pEnabled = "false", nEnabled = "false";
	// if (equip.getPage() * Constants.SEARCH_PAGE_SIZE < equip
	// .getRowCount()) {
	// model.addAttribute("next", new Boolean(true));
	// nEnabled = "true";
	// } else {
	// model.addAttribute("next", new Boolean(false));
	// }
	//
	// if (equip.getPage() >= 2) {
	// model.addAttribute("prev", new Boolean(true));
	// pEnabled = "true";
	// } else {
	// model.addAttribute("prev", new Boolean(false));
	// }
	//
	// model.addAttribute("equip", equip);
	//
	// // s = getWorkOrderSearchForm(model);
	// } catch (ServiceException ems) {
	// logger.catching(ems);
	//
	// resp.setStatus(500);
	// model.addAttribute(Constants.MODEL_ATTR_EXCEPTION, ems.getMessage());
	// tmpl = Constants.INLINE_ERRORS_JSP;
	// }
	//
	// logger.exit();
	//
	// return "equipment/EquipmentsTable.jsp";
	// }

}
