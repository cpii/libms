package com.hurontg.libms.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USERS")
public class AuthUser extends BaseEntity implements UserDetails,
		CredentialsContainer, Auditable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private String username;
	private String firstName;
	private String middleName;
	private String lastName;
	private String password;
	private String salt;
	private String email;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private int failedLoginAttempts;
	private boolean enabled;
	private Set<GrantedAuthority> authorities;

	private Set<Group> groups = new HashSet<Group>();
	private Set<Authority> customAuthorities = new HashSet<Authority>();
	private Long userSessionId; // the primary key to the USER_SESSION table
	private List<BorrowedItem> borrowedItems = new ArrayList<BorrowedItem>();

	/**
	 * 
	 */
	public AuthUser() {
		super();
		accountNonExpired = true;
		accountNonLocked = true;
		credentialsNonExpired = true;
		failedLoginAttempts = 0;
		enabled = true;
	}

	/**
	 * 
	 * @param id
	 * @param username
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param password
	 * @param salt
	 * @param email
	 * @param authorities
	 */
	public AuthUser(Long id, String username, String firstName,
			String middleName, String lastName, String password, String salt,
			String email, Collection<? extends GrantedAuthority> authorities) {
		this(id, username, firstName, middleName, lastName, password, salt,
				email, true, true, true, true, authorities,
				new HashSet<Group>());
	}

	/**
	 * 
	 * @param id
	 * @param username
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param password
	 * @param salt
	 * @param email
	 * @param accountNonExpired
	 * @param accountNonLocked
	 * @param credentialsNonExpired
	 * @param enabled
	 * @param authorities
	 */
	public AuthUser(Long id, String username, String firstName,
			String middleName, String lastName, String password, String salt,
			String email, boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled,
			Collection<? extends GrantedAuthority> authorities,
			Set<Group> groups) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}

		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = Collections
				.unmodifiableSet(sortAuthorities(authorities));
		this.groups = groups;
	}

	public AuthUser(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USERS_ID")
	public Long getId() {
		return id;
	}

	/**
	 * @return the username
	 */
	@Column(name = "USERNAME", unique = true, nullable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "FIRST_NAME", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the middleName
	 */
	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return the lastName
	 */
	@Column(name = "LAST_NAME", nullable = false)
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @return the salt
	 */
	@Column(name = "SALT", nullable = false)
	public String getSalt() {
		return salt;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EMAIL", nullable = false)
	public String getEmail() {
		return email;
	}

	/**
	 * @return the accountNonExpired
	 */
	@Column(name = "ACCOUNT_NON_EXPIRED", columnDefinition = "bit")
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	@Column(name = "ACCOUNT_NON_LOCKED", columnDefinition = "bit")
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	@Column(name = "CREDENTIALS_NON_EXPIRED", columnDefinition = "bit")
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @return the failedLoginAttempts
	 */
	@Column(name = "FAILED_LOGIN_ATTEMPTS")
	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	/**
	 * @param failedLoginAttempts
	 *            the failedLoginAttempts to set
	 */
	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	/**
	 * @return the enabled
	 */
	@Column(name = "ENABLED", nullable = false, columnDefinition = "bit")
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 */
	public void eraseCredentials() {
		password = null;
	}

	/**
	 * @return the authorities
	 */
	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Transient
	public String getDisplayName() {
		return firstName + " " + lastName;
	}

	/**
	 * 
	 * @param authorities
	 * @return
	 */
	private static SortedSet<GrantedAuthority> sortAuthorities(
			Collection<? extends GrantedAuthority> authorities) {
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(
				new AuthorityComparator());

		for (GrantedAuthority grantedAuthority : authorities) {
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}

	/**
	 * 
	 * @author mansoork
	 * 
	 */
	private static class AuthorityComparator implements
			Comparator<GrantedAuthority>, Serializable {
		private static final long serialVersionUID = -1L;

		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before
			// adding it to the set.
			// If the authority is null, it is a custom authority and should
			// precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}

			if (g1.getAuthority() == null) {
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code User} instance
	 * with the same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username,
	 * representing the same principal.
	 */
	@Override
	public boolean equals(Object rhs) {
		if (rhs instanceof AuthUser) {
			return username.equals(((AuthUser) rhs).username);
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return username != null ? username.hashCode() : -1;
	}

	/**
	 * @return the userSessionId
	 */
	public Long getUserSessionId() {
		return userSessionId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param accountNonExpired
	 *            the accountNonExpired to set
	 */
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @param accountNonLocked
	 *            the accountNonLocked to set
	 */
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @param credentialsNonExpired
	 *            the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @param authorities
	 *            the authorities to set
	 */
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param userSessionId
	 *            the userSessionId to set
	 */
	public void setUserSessionId(Long userSessionId) {
		this.userSessionId = userSessionId;
	}

	/**
	 * @return the groups
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "GROUP_MEMBERS", joinColumns = { @JoinColumn(name = "USERS_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 *            the groups to set
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	/**
	 * @return the customAuthorities
	 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "CUSTOM_AUTHORITY", joinColumns = { @JoinColumn(name = "USERS_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	public Set<Authority> getCustomAuthorities() {
		return customAuthorities;
	}

	/**
	 * @param customAuthorities
	 *            the customAuthorities to set
	 */
	public void setCustomAuthorities(Set<Authority> customAuthorities) {
		this.customAuthorities = customAuthorities;
	}

	@OneToMany(mappedBy = "member", cascade = { CascadeType.MERGE })
	public List<BorrowedItem> getBorrowedItems() {
		return borrowedItems;
	}

	public void setBorrowedItems(List<BorrowedItem> borrowedItems) {
		this.borrowedItems = borrowedItems;
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
		result.append(" First Name: ").append(firstName).append(NEW_LINE);
		result.append(" Middle Name: ").append(middleName).append(NEW_LINE);
		result.append(" Last Name: ").append(lastName).append(NEW_LINE);
		result.append(" Username: ").append(username).append(NEW_LINE);
		result.append(" Enabled: ").append(enabled).append(NEW_LINE);
		result.append(" Account Not Expired: ").append(accountNonExpired)
				.append(NEW_LINE);
		result.append(" Credential Not Expired: ")
				.append(credentialsNonExpired).append(NEW_LINE);
		result.append(" Account Not Locked: ").append(accountNonLocked)
				.append(NEW_LINE);

		if (authorities != null && !authorities.isEmpty()) {
			result.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					result.append(",");
				}
				first = false;

				result.append(auth);
			}
		} else {
			result.append("Not granted any authorities; ");
		}

		result.append(NEW_LINE).append("}");

		return result.toString();
	}

	/**
	 * 
	 * @param group
	 */
	public void addGroup(Group group) {
		groups.add(group);
		group.getUsers().add(this);
	}
}
