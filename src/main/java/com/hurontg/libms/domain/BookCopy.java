package com.hurontg.libms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "BOOK_COPY", indexes = { @Index(columnList = "BOOK_ID", name = "IDX__BOOK_COPY_BOOK_ID") }, uniqueConstraints = { @UniqueConstraint(columnNames = {
		"BOOK_ID", "SEQUENCE_ID" }, name = "UQ_BOOK_SEQUENCE") })
public class BookCopy extends BaseEntity implements Auditable {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private String sequenceId;
	private String location;
	private String condition;
	private boolean available;
	private List<BorrowedItem> borrowedItems = new ArrayList<BorrowedItem>();
	private Book book = new Book();

	/**
	 * 
	 */
	public BookCopy() {
	}

	public BookCopy(String sequenceId, String location, String condition,
			boolean available, Book book) {
		this.sequenceId = sequenceId;
		this.location = location;
		this.condition = condition;
		this.available = available;
		this.book = book;

	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOKCOPY_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SEQUENCE_ID", nullable = false)
	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	@Column(name = "LOCATION", nullable = false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "CONDITION", nullable = false)
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Column(name = "IS_AVAILBLE", nullable = false)
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@OneToMany(mappedBy = "bookCopy", fetch = FetchType.LAZY, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE })
	public List<BorrowedItem> getBorrowedItems() {
		return borrowedItems;
	}

	public void setBorrowedItems(List<BorrowedItem> borrowedItems) {
		this.borrowedItems = borrowedItems;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "BOOK_ID", foreignKey = @ForeignKey(foreignKeyDefinition = "FOREIGN KEY (BOOK_ID) REFERENCES BOOK", name = "FK__BOOK_BOOK_ID"))
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
		result.append(" Sequence ID: ").append(sequenceId).append(NEW_LINE);
		result.append(" Location: ").append(location).append(NEW_LINE);
		result.append(" Condition: ").append(condition).append(NEW_LINE);
		result.append(" Available: ").append(available).append(NEW_LINE);
		result.append(NEW_LINE).append("}");

		return result.toString();
	}

	/**
	 * 
	 * @param member
	 */
	public void borrowForMember(AuthUser member) {
		BorrowedItem bi = new BorrowedItem(this, member);
		borrowedItems.add(bi);
		available = false;
	}

}
