package com.hurontg.libms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BOOK")
public class Book extends BaseEntity implements Auditable {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private String title;
	private String isbn;
	private double cost;
	private Date published;
	private String keywords;
	private boolean available = false;
	private Publisher publisher;
	private List<BookCopy> copies = new ArrayList<BookCopy>();

	private List<Author> authors = new ArrayList<Author>();

	/**
	 * 
	 */
	public Book() {
	}

	public Book(Integer id) {
		this.id = new Long(id);
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BOOK_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "COST")
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "ISBN", unique = true, nullable = false)
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE_PUBLISHED", nullable = false)
	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	@Column(name = "KEYWORDS")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PUBLISHER_ID", nullable = false, foreignKey = @ForeignKey(name = "FK__PUBLISHER_PUBLISHER_ID"))
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@ManyToMany
	@JoinTable(name = "BOOK_AUTHOR", joinColumns = { @JoinColumn(name = "BOOK_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHOR_ID") })
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true, mappedBy = "book")
	public List<BookCopy> getCopies() {
		return copies;
	}

	public void setCopies(List<BookCopy> copies) {
		this.copies = copies;
	}

	@Transient
	public String getAuthorNames() {
		StringBuilder sb = new StringBuilder();
		for (Author a : authors) {
			sb.append(a.getFirstName()).append(" ").append(a.getLastName());
		}
		return sb.toString();
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
		result.append(" Title: ").append(title).append(NEW_LINE);
		result.append(" ISBN: ").append(isbn).append(NEW_LINE);
		result.append(" Date Published: ").append(published).append(NEW_LINE);
		result.append(" Cost: ").append(cost).append(NEW_LINE);
		result.append(" Available: ").append(available).append(NEW_LINE);

		result.append(NEW_LINE).append("}");

		return result.toString();
	}

	public void setAvailable(boolean b) {
		available = b;

	}

	@Transient
	public boolean isAvailable() {
		return available;
	}
	
	public void createCopy() {
		UUID uuid = UUID.randomUUID();
		BookCopy bc = new BookCopy(uuid.toString(), "125S", "New", true, this);
		copies.add(bc);
	}
}
