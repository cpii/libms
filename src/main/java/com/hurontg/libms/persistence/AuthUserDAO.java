package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Authority;

public interface AuthUserDAO extends GenericDAO<AuthUser> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	public AuthUser findUserByUsername(String username);

	/**
	 * Adds a row in GROUP_MEMBER table
	 * 
	 * @param user
	 * @param group
	 */
	public void addGroupMembership(AuthUser user, String group);

	/**
	 * This will load both Group and Custom Authorities
	 * 
	 * @param username
	 * @return
	 */
	public List<Authority> loadGroupAuthorities(String username);
	
	public List<AuthUser> loadMembers();

}
