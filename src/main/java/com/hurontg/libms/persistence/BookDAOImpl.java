package com.hurontg.libms.persistence;

import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.common.util.Utils;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Book;
import com.hurontg.libms.domain.BookCopy;
import com.hurontg.libms.domain.BorrowedItem;

@Repository
public class BookDAOImpl extends AbstractGenericDAOImpl<Book> implements
		BookDAO {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(BookDAOImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hurontg.focus.persistence.AuthUserDAO#
	 * getAuthorityIdByAuthorityName(java.lang.String)
	 */
	@Override
	public List<Book> getBooks() {
		logger.entry();

		List<Book> books = null;

		try {
			books = getEntityManager().createQuery(
					"select b from Book b" + " left join fetch b.publisher"
							+ " left join fetch b.authors", Book.class)
					.getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve Books", re);
		}

		logger.exit();

		return books;
	}

	@Override
	public List<Book> getAvailableBooks() {
		logger.entry();

		List<Book> books = null;

		try {
			books = getEntityManager().createQuery(
					"select b from Book b where b in ("
							+ " 	select bc.book from BookCopy bc "
							+ " 		where bc.available = true)", Book.class)
					.getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve Books", re);
		}

		logger.exit();

		return books;
	}

	@Override
	public List<Book> searchBooks(String term) {
		List<Book> books = null;

		try {
			books = getEntityManager()
					.createQuery(
							"select b from Book b"
									+ " where b.title like :searchTerm",
							Book.class)
					.setParameter("searchTerm", "%" + term + "%")
					.getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve Books", re);
		}

		return books;
	}

	@Override
	public BookCopy findAvailableBookCopy(Long bookId) {
		BookCopy bc = null;

		try {
			bc = getEntityManager()
					.createQuery(
							"select bc from BookCopy bc"
									+ " where bc.book.id = :bookId "
									+ " and bc.available = true",
							BookCopy.class).setParameter("bookId", bookId)
					.setFirstResult(0).setMaxResults(1).getSingleResult();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve available BookCopy",
					re);
		}

		return bc;
	}

	@Override
	public List<Book> findCheckedOutBooksByUser() {
		List<Book> books = null;

		try {
			AuthUser authUser = Utils.getAuthUser();
			books = getEntityManager()
					.createQuery(
							"select b from Book b left join fetch b.copies bc "
									+ " left join fetch bc.borrowedItems bi "
									+ " where bi.member = :member"
									+ " and bi.returnedDate is null",
							Book.class).setParameter("member", authUser)
					.getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException(
					"Failed to retrieve Books borrowed by current user", re);
		}

		return books;
	}

	@Override
	public List<BorrowedItem> findBorrowedItemsByUser() {
		List<BorrowedItem> items = null;

		try {
			AuthUser authUser = Utils.getAuthUser();
			items = getEntityManager()
					.createQuery(
							"select bi from BorrowedItem bi "
									+ " left join fetch bi.bookCopy bc "
									+ " left join fetch bc.book "
									+ " where bi.member = :member"
									+ " and bi.returnedDate is null",
							BorrowedItem.class)
					.setParameter("member", authUser).getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException(
					"Failed to retrieve Books borrowed by current user", re);
		}

		return items;
	}

	@Override
	public BorrowedItem findByBorrowedItemById(Long borrowedItemId) {
		BorrowedItem bi = null;

		try {
			bi = getEntityManager()
					.createQuery(
							"select bi from BorrowedItem bi left join fetch bi.bookCopy where bi.id = :id",
							BorrowedItem.class)
					.setParameter("id", borrowedItemId).getSingleResult();
		} catch (RuntimeException re) {
			throw new AppDAOException(
					"Failed to retrieve BorrowedItem by current user", re);
		}

		return bi;
	}
}
