package com.hurontg.libms.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
public class Authority {
	// Roles
	public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";
	public static final String ROLE_STAFF = "ROLE_STAFF";
	public static final String ROLE_MEMBER = "ROLE_MEMBER";
	public static final String ROLE_USER = "ROLE_USER";
	
	private Long id;
	private String authority;

	private Set<AuthUser> users = new HashSet<AuthUser>();

	/**
	 * 
	 */
	public Authority() {
	}

	/**
	 * 
	 * @param authority
	 */
	public Authority(String authority) {
		this.authority = authority;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUTHORITY_ID")
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the authority
	 */
	@Column(name = "AUTHORITY", nullable = false, unique = true)
	public String getAuthority() {
		return authority;
	}

	/**
	 * @param authority
	 *            the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	/**
	 * @return the users
	 */
	@ManyToMany(mappedBy = "customAuthorities")
	public Set<AuthUser> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(Set<AuthUser> users) {
		this.users = users;
	}

	/**
     * 
     */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" Authority: ").append(authority).append(NEW_LINE);
		result.append(NEW_LINE).append("}");

		return result.toString();
	}

}
