package com.hurontg.libms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ITEM_HOLD")
public class ItemHold extends BaseEntity implements Auditable {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private Book book;
	private BookCopy bookCopy;
	private AuthUser member;
	private Date requestDate;
	private Date dateSatisfied;

	/**
	 * 
	 */
	public ItemHold() {
	}

	public ItemHold(Book book, AuthUser member) {
		this.book = book;
		this.member = member;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEMHOLD_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOKCOPY_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__BOOKCOPY_BOOKCOPY_ID"))
	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__USERS_USERS_ID"))
	public AuthUser getMember() {
		return member;
	}

	public void setMember(AuthUser member) {
		this.member = member;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOK_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__BOOK_BOOK_ID"))
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "REQUEST_DATE", nullable = false)
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "SATISFIED_DATE")
	public Date getDateSatisfied() {
		return dateSatisfied;
	}

	public void setDateSatisfied(Date dateSatisfied) {
		this.dateSatisfied = dateSatisfied;
	}

	/**
     * 
     */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName()).append(" Object {")
				.append(NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" Book ID: ").append(book.getId()).append(NEW_LINE);
		result.append(" Member ID: ").append(member.getId()).append(NEW_LINE);
		result.append(" Requested Date: ").append(requestDate).append(NEW_LINE);
		result.append(" Date Satisfied: ").append(dateSatisfied)
				.append(NEW_LINE);

		result.append(NEW_LINE).append("}");

		return result.toString();
	}

}
