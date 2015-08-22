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

import com.hurontg.libms.persistence.BookDAO;


@Service
public class MagServiceImpl {//implements BookService {
	public List<Book> getBooks()
	{
		return null;
	}
	public Book createBook(Book book)
	{
		return null;
	}
	public Book getBookById(Integer id)
	{
		return null;
	}
	public Book updateBook(Book book)
	{
		return null;
	}
	public void deleteBook(Book book)
	{
		return;
	}
	
	public List<Book> searchBooks(String string)
	{
		return null;
	}

	public void checkout(Long bookId, Long memberId)
	{
		return ;
	}

	public List<Book> getBooksCheckoutByUser()
	{
		return null;
	}

	public void returnBook(Long borrowedItemId)
	{
		return;
	}

	public List<BorrowedItem> getBorrowedItemsByUser()
	{
		return null;
	}

	public Book createBook(Book book, int copies)
	{
		return null;
	}


}
