package com.hurontg.libms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "AUTHOR")
public class Author extends BaseEntity implements Auditable {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender; // 'M' or 'F'
	private Date birthday;
	private List<Book> books = new ArrayList<Book>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUTHOR_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "GENDER", nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY", nullable = false)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@ManyToMany(mappedBy = "authors")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Transient
	public String getName() {
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName()).append(" Object {")
				.append(NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" First Name: ").append(firstName).append(NEW_LINE);
		result.append(" Middle Name: ").append(middleName).append(NEW_LINE);
		result.append(" Last Name: ").append(lastName).append(NEW_LINE);
		result.append(" Gender: ").append(gender).append(NEW_LINE);
		result.append(" Birthday: ").append(birthday).append(NEW_LINE);

		result.append(NEW_LINE).append("}");

		return result.toString();
	}
}
