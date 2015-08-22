package com.hurontg.libms.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Authority;
import com.hurontg.libms.persistence.AuthUserDAO;

public class CustomJdbcDaoImpl extends JdbcDaoImpl {
	private XLogger logger = XLoggerFactory.getXLogger(CustomJdbcDaoImpl.class
			.getName());

	private AuthUserDAO userDao;

	/**
	 * @param userDao
	 *            the userDao to set
	 */
	@Inject
	public void setUserDao(AuthUserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * 
	 */
	@Override
	protected UserDetails createUserDetails(String username,
			UserDetails userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities) {
		logger.entry(username, userFromUserQuery);

		String returnUsername = userFromUserQuery.getUsername();

		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}

		AuthUser au = new AuthUser(((AuthUser) userFromUserQuery).getId(),
				returnUsername, ((AuthUser) userFromUserQuery).getFirstName(),
				((AuthUser) userFromUserQuery).getMiddleName(),
				((AuthUser) userFromUserQuery).getLastName(),
				((AuthUser) userFromUserQuery).getPassword(),
				((AuthUser) userFromUserQuery).getSalt(),
				((AuthUser) userFromUserQuery).getEmail(),
				((AuthUser) userFromUserQuery).isAccountNonExpired(),
				((AuthUser) userFromUserQuery).isAccountNonLocked(),
				((AuthUser) userFromUserQuery).isCredentialsNonExpired(),
				((AuthUser) userFromUserQuery).isEnabled(),
				combinedAuthorities, ((AuthUser) userFromUserQuery).getGroups());

		return au;
	}

	/**
	 * 
	 */
	@Override
	public List<UserDetails> loadUsersByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		logger.entry(username);

		List<UserDetails> ud = new ArrayList<UserDetails>();

		try {
			AuthUser user = userDao.findUserByUsername(username);
			ud.add(user);
		} catch (RuntimeException re) {
			throw new UsernameNotFoundException("A user with user-name, "
					+ username + " could not be found", re);
		}

		logger.exit();

		return ud;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl#
	 * loadGroupAuthorities(java.lang.String)
	 */
	@Override
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		logger.entry();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		try {
			List<Authority> groupAuthorities = userDao
					.loadGroupAuthorities(username);
			for (Authority authority : groupAuthorities) {
				grantedAuthorities.add(new SimpleGrantedAuthority(authority
						.getAuthority()));
			}
		} catch (AppDAOException fde) {
			throw new AppServiceException("Failed to load group authorities",
					fde);
		}

		logger.exit();

		return grantedAuthorities;
	}

	@Override
	protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities) {
		int dummy = 9;
		dummy++;
	}
}
