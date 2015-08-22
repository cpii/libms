package com.hurontg.libms.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {
	public static final String GROUPNAME_ADMINISTRATORS = "Administrators";	
	public static final String GROUPNAME_MEMBERS = "Members";
	public static final String GROUPNAME_STAFF = "Staff";
	
	private static final long serialVersionUID = -303067606088159L;
	private Long id;
	private String name;
	private Set<Authority> authorities = new HashSet<Authority>();

	private Set<AuthUser> users = new HashSet<AuthUser>();
	
	
		

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID")
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
	 * @return the name
	 */
	@Column(name = "NAME", nullable = false, unique = true)
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the authorities
	 */
	@ManyToMany
	@JoinTable(name = "GROUP_AUTHORITY", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the users
	 */
	@ManyToMany(mappedBy = "groups")
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

		result.append(this.getClass().getName()).append(" Object {").append(NEW_LINE);
		result.append(" id: ").append(id).append(NEW_LINE);
		result.append(" Name: ").append(name).append(NEW_LINE);
		result.append("}");

		return result.toString();
	}

}
