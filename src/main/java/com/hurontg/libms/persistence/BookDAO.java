package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.Book;
import com.hurontg.libms.domain.BookCopy;
import com.hurontg.libms.domain.BorrowedItem;

public interface BookDAO extends GenericDAO<Book> {
	public List<Book> getBooks();
	public List<Book> getAvailableBooks();

	public List<Book> searchBooks(String query);
	public BookCopy findAvailableBookCopy(Long bookId);
	public List<Book> findCheckedOutBooksByUser();
	public BorrowedItem findByBorrowedItemById(Long borrowedItemId);

	public List<BorrowedItem> findBorrowedItemsByUser();

}
