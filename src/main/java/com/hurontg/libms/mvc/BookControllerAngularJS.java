package com.hurontg.libms.mvc;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.service.BookService;

@Controller
public class BookControllerAngularJS extends BaseController {

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(BookControllerAngularJS.class
			.getName());
	@Inject
	private BookService bookSvc;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = { "/books" }, method = RequestMethod.GET)
	public @ResponseBody List<Book> getBooks(Model model) throws Exception {
		logger.entry();
		List<Book> books = bookSvc.getBooks();
		logger.exit();
		return books;
	}

	@RequestMapping(value = { "/books" }, method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> postBook(@RequestBody Book book, Model model)
			throws Exception {
		logger.entry();
		Book bk = bookSvc.createBook(book);
		logger.exit();
		return new ResponseEntity<Book>(bk, HttpStatus.OK);
	}

	@RequestMapping(value = { "/books/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@PathVariable("id") Integer id, Model model)
			throws Exception {
		logger.entry();
		Book bk = bookSvc.getBookById(id);
		logger.exit();
		return new ResponseEntity<Book>(bk, HttpStatus.OK);
	}

	@RequestMapping(value = { "/books/{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> putBook(@PathVariable("id") Integer id,
			@RequestBody Book book, Model model) throws Exception {
		logger.entry();
		Book bk = bookSvc.updateBook(book);
		logger.exit();
		return new ResponseEntity<Book>(bk, HttpStatus.OK);
	}

	@RequestMapping(value = { "/books/{id}" }, method = RequestMethod.DELETE, produces = "text/plain")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Integer id,
			Model model) throws Exception {
		logger.entry();
		bookSvc.deleteBook(new Book(id));
		logger.exit();
		return new ResponseEntity<String>("Book deleted", HttpStatus.OK);
	}
*/
}
