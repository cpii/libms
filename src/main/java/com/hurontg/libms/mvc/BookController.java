package com.hurontg.libms.mvc;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Author;
import com.hurontg.libms.domain.Book;
import com.hurontg.libms.domain.Publisher;
import com.hurontg.libms.service.AuthorService;
import com.hurontg.libms.service.BookService;
import com.hurontg.libms.service.PublisherService;
import com.hurontg.libms.service.UserService;

@Controller
public class BookController extends BaseController {

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(BookController.class
			.getName());

	@Inject
	private BookService bookSvc;
	@Inject
	private PublisherService pubSvc;
	@Inject
	private AuthorService authSvc;
	@Inject
	private UserService userSvc;

	@RequestMapping(value = { "/books" }, method = RequestMethod.GET)
	public String showBooksMainPage(Model model) throws Exception {
		try {
		List<Book> books = bookSvc.getBooks();
		model.addAttribute("books", books);
		
		List<AuthUser> members = userSvc.loadMembers();
		model.addAttribute("members", members);
		} catch (AppServiceException e) {
			logger.error("Error while retrieving Books", e);
		}
		return "book-main";
	}

	@RequestMapping(value = { "/books/new" }, method = RequestMethod.GET)
	public String showBookEntryForm(Model model) throws Exception {
		List<Publisher> pubs = pubSvc.findAll();
		model.addAttribute("publishers", pubs);

		List<Author> authors = authSvc.findAll();
		model.addAttribute("authors", authors);

		Book book = new Book();
		model.addAttribute("book", book);

		return "book-new";
	}

	@RequestMapping(value = { "/books/save" }, method = RequestMethod.POST)
	public String saveNewBook(@Valid Book book, BindingResult result,
			HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			return "book-new";
		} else {
			String copies = request.getParameter("bkcopies");
			if (copies != null) {
				int cp = Integer.parseInt(copies);
				bookSvc.createBook(book, cp);
			} else {
				bookSvc.createBook(book);
			}
			
			return "redirect:/books";
		}
	}

	@RequestMapping(value = { "/books/checkout" }, method = RequestMethod.POST)
	public String checkoutBook(	HttpServletRequest request) throws Exception {
		Long bookId = Long.parseLong(request.getParameter("book.id"));
		Long memberId = Long.parseLong(request.getParameter("member.id"));
		bookSvc.checkout(bookId, memberId);
		return "redirect:/books";
		
	}
	
	@RequestMapping(value = { "/books/search" }, method = RequestMethod.POST)
	public String searchBooks(HttpServletRequest request) throws Exception {
		Map<java.lang.String,java.lang.String[]> formData = request.getParameterMap();
		String[] searchQuery = formData.get("q");
		List<Book> books = bookSvc.searchBooks(searchQuery[0]);

		request.setAttribute("books", books);
		return "book-main";
	}
	
	@RequestMapping(value = { "/books/return" }, method = RequestMethod.GET)
	public String returnBook(HttpServletRequest request) throws Exception {
		Long borrowedItemId = Long.parseLong(request.getParameter("borrowedItemId"));
		bookSvc.returnBook(borrowedItemId);
		
		return "redirect:/home";
	}
	
	/*
	 * Ajax
	 */
	@RequestMapping(value = { "/public/ajaxdemo" }, method = RequestMethod.GET)
	public String ajaxDemoPage(HttpServletRequest request) throws Exception {
		return "tutorial/ajax/demopage.jsp";
	}
	
	@RequestMapping(value = { "/public/ajax/simpletextline" }, method = RequestMethod.GET)
	public String ajaxReturnSimpleLine(HttpServletRequest request) throws Exception {
		request.setAttribute("serverTime", new Date());
		return "tutorial/ajax/simple1.jsp";
	}
	
	@RequestMapping(value = { "/public/ajax/dynamicontent" }, method = RequestMethod.GET)
	public String ajaxBookTable(HttpServletRequest request) throws Exception {
		List<Book> books = bookSvc.getBooks();
		request.setAttribute("books", books);
		return "tutorial/ajax/booktable.jsp";
	}
	
	@RequestMapping(value = { "/public/ajax/formsubmit1" }, method = RequestMethod.POST)
	public /*ResponseEntity<String>*/ String ajaxSubmitFormHandler1(HttpServletRequest request, HttpServletResponse resp) throws Exception {
		Map<String, String[]> requestParams = request.getParameterMap();
		
		boolean simulateError = false;
		for(Map.Entry<String, String[]> entry : requestParams.entrySet()) {
			String name = entry.getKey();
			String[] value = entry.getValue();
			
			logger.error(name + " : " + value[0]);
			
			if (name.equals("simulate-error")) {
				simulateError = true;
			}
		}
		
		if (simulateError) {
//			resp.setStatus(sc);
//			return new ResponseEntity<String>("Error encountered on server!",  HttpStatus.INTERNAL_SERVER_ERROR);
		}
//		return new ResponseEntity<String>("Form successfully processed!", HttpStatus.OK);
		List<Book> books = bookSvc.getBooks();
		request.setAttribute("books", books);
		return "tutorial/ajax/booktable.jsp";
				
	}
	
	
	// @RequestMapping(value = { "/displayPublisherUpdateForm" }, method =
	// RequestMethod.GET)
	// public String showPublisherUpdateForm(HttpServletRequest request)
	// throws Exception {
	// String id = request.getParameter("bookId");
	// Long bookId = Long.parseLong(id);
	// Publisher book = bookSvc.findById(bookId);
	//
	// request.setAttribute("book", book);
	// return "book/bookUpdateForm.jsp";
	// }

	// @RequestMapping(value = { "/displayPublisherUpdateForm" }, method =
	// RequestMethod.GET)
	// public String showPublisherUpdateForm(HttpServletRequest request)
	// throws Exception {
	// String id = request.getParameter("bookId");
	// Long bookId = Long.parseLong(id);
	// Publisher book = bookSvc.findById(bookId);
	//
	// request.setAttribute("book", book);
	// return "book/book.jsp";
	// }

	// @RequestMapping(value = { "/updatePublisher" }, method =
	// RequestMethod.POST)
	// public String updatePublisher(HttpServletRequest request) throws
	// Exception {
	// String id = request.getParameter("id");
	// String name = request.getParameter("name");
	// String email = request.getParameter("email");
	// String phone = request.getParameter("phone");
	//
	// Publisher p = new Publisher(Long.parseLong(id), name, email, phone);
	// bookSvc.update(p);
	//
	// return "redirect:/books";
	// }

	// @RequestMapping(value = { "/updatePublisher" }, method =
	// RequestMethod.POST)
	// public String updatePublisher(@ModelAttribute Publisher book,
	// HttpServletRequest request) throws Exception {
	// bookSvc.update(book);
	//
	// return "redirect:/books";
	// }
	//
	// @RequestMapping(value = { "/deletePublisher" }, method =
	// RequestMethod.GET)
	// public String deletePublisher(HttpServletRequest request) throws
	// Exception {
	// String id = request.getParameter("bookId");
	// bookSvc.delete(Long.parseLong(id));
	// return "redirect:/books";
	// }

}
