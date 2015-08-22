package com.hurontg.libms.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Book;
import com.hurontg.libms.domain.BookCopy;
import com.hurontg.libms.domain.BorrowedItem;
import com.hurontg.libms.persistence.AuthUserDAO;
import com.hurontg.libms.persistence.BookDAO;

@Service
public class BookServiceImpl implements BookService {
	@Inject
	private BookDAO bookDao;
	@Inject
	private AuthUserDAO userDao;

	@Override
	public List<Book> getBooks() {
		List<Book> books = null;
		try {
			books = bookDao.getBooks();

			List<Book> availableBooks = bookDao.getAvailableBooks();

			// Save the id's of available books
			List<Long> availableBookIds = new ArrayList<Long>();
			for (Book book : availableBooks) {
				availableBookIds.add(book.getId());
			}

			// Now mark the books for availability
			for (Book book : books) {
				if (availableBookIds.contains(book.getId())) {
					book.setAvailable(true);
				}
			}
		} catch (AppDAOException e) {
			throw new AppServiceException(e);
		}
		return books;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book createBook(Book book) {
		Book bk = null;
		try {
			bk = bookDao.makePersistent(book);
		} catch (AppDAOException e) {
			throw new AppServiceException(e);
		}
		return bk;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book createBook(Book book, int copies) {
		Book bk = null;
		try {
			for (int i=copies; i>0; i--) {
				book.createCopy();
			}
			bk = bookDao.makePersistent(book);
		} catch (AppDAOException e) {
			throw new AppServiceException(e);
		}	
		return bk;
	}

	@Override
	public Book getBookById(Integer id) {
		Book book = bookDao.findById(new Long(id), false);
		return book;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book updateBook(Book book) {
		return bookDao.mergeState(book, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteBook(Book book) {
		Book bk = bookDao.findById(book.getId(), false);
		bookDao.makeTransient(bk);

	}

	@Override
	public List<Book> searchBooks(String query) {
		List<Book> books = null;
		try {
			books = bookDao.searchBooks(query);
		} catch (AppDAOException e) {
			throw new AppServiceException(e);
		}
		return books;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void checkout(Long bookId, Long memberId) {
		try {
			// Get available book_copy for the book
			BookCopy bc = bookDao.findAvailableBookCopy(bookId);
			bc.borrowForMember(new AuthUser(memberId));
		} catch (AppDAOException e) {
			throw new AppServiceException("Failed to check out Book", e);
		}
	}

	@Override
	public List<Book> getBooksCheckoutByUser() {
		List<Book> checkedOutBooks = null;
		try {
			checkedOutBooks = bookDao.findCheckedOutBooksByUser();
		} catch (AppDAOException e) {
			throw new AppServiceException("Failed to find checked out books", e);
		}

		return checkedOutBooks;
	}

	@Override
	public List<BorrowedItem> getBorrowedItemsByUser() {
		List<BorrowedItem> items = null;
		try {
			items = bookDao.findBorrowedItemsByUser();
		} catch (AppDAOException e) {
			throw new AppServiceException("Failed to find checked out books", e);
		}

		return items;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void returnBook(Long borrowedItemId) {
		try {
			BorrowedItem bi = bookDao.findByBorrowedItemById(borrowedItemId);
			bi.returnBook();
			// bookCopyDao.mergeState(bc, false);
		} catch (AppDAOException e) {
			throw new AppServiceException("Failed to return book", e);
		}

	}

}
