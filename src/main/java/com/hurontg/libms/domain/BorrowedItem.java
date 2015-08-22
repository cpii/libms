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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BORROWED_ITEM")
public class BorrowedItem extends BaseEntity implements Auditable {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private BookCopy bookCopy;
	private AuthUser member;
	private Date borrowedDate;
	private Date dueDate;
	private Date returnedDate;
	private String returnedCondition;
	private Integer renewCount;

	/**
	 * 
	 */
	public BorrowedItem() {
	}

	public BorrowedItem(BookCopy bookCopy, AuthUser member) {
		this.bookCopy = bookCopy;
		this.member = member;
		this.borrowedDate = new Date();
		this.dueDate = DateUtils.addDays(borrowedDate, 14); // BAD_PRACTICE
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BORROWEDITEM_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "BOOKCOPY_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__BOOKCOPY_BOOKCOPY_ID"))
	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__USERS_USERS_ID"))
	public AuthUser getMember() {
		return member;
	}

	public void setMember(AuthUser member) {
		this.member = member;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "BORROWED_DATE", nullable = false)
	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", nullable = false)
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "RETURNED_DATE")
	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}

	@Column(name = "RETURNED_CONDITIONED")
	public String getReturnedCondition() {
		return returnedCondition;
	}

	public void setReturnedCondition(String returnedCondition) {
		this.returnedCondition = returnedCondition;
	}

	@Column(name = "RENEW_COUNT")
	public Integer getRenewCount() {
		return renewCount;
	}

	public void setRenewCount(Integer renewCount) {
		this.renewCount = renewCount;
	}

	public void returnBook() {
		returnedDate = new Date();
		returnedCondition = "Good";
		bookCopy.setAvailable(true);
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
		result.append(" Borrowed Date: ").append(borrowedDate).append(NEW_LINE);
		result.append(" Due Date: ").append(dueDate).append(NEW_LINE);
		result.append(" Returned Date: ").append(returnedDate).append(NEW_LINE);
		result.append(" Renewal Count: ").append(renewCount).append(NEW_LINE);
		result.append(" Condition Returned: ").append(returnedCondition)
				.append(NEW_LINE);
		result.append(NEW_LINE).append("}");

		return result.toString();
	}

}
