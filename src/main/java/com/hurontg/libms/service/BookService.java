package com.hurontg.libms.service;

import java.util.List;

import com.hurontg.libms.domain.Book;
import com.hurontg.libms.domain.BorrowedItem;

public interface BookService {
	List<Book> getBooks();
	Book createBook(Book book);
	Book getBookById(Integer id);
	Book updateBook(Book book);
	void deleteBook(Book book);
	List<Book> searchBooks(String string);
	void checkout(Long bookId, Long memberId);
	List<Book> getBooksCheckoutByUser();
	void returnBook(Long borrowedItemId);
	List<BorrowedItem> getBorrowedItemsByUser();
	Book createBook(Book book, int copies);
}
