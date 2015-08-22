package com.hurontg.libms.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class Publisher extends BaseEntity implements Auditable {
	private Long id;
	private String name;
	private String email;
	private String phone;

	private List<Book> books = new ArrayList<Book>();

	public Publisher() {
		
	}
	public Publisher(Long id, String name, String email, String phone) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
				
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PUBLISHER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "publisher")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Column(name = "EMAIL", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", nullable = false, unique = true)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName()).append(" Object {")
				.append(NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" Name: ").append(name).append(NEW_LINE);
		result.append(" Email: ").append(email).append(NEW_LINE);
		result.append(" Phone: ").append(phone).append(NEW_LINE);
		result.append("}");

		return result.toString();
	}

}
