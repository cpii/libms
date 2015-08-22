package com.hurontg.libms.persistence;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.domain.Authority;
import com.hurontg.libms.domain.Group;

@Repository
public class AuthUserDAOImpl extends AbstractGenericDAOImpl<AuthUser> implements
		AuthUserDAO {
	private XLogger logger = XLoggerFactory.getXLogger(AuthUserDAOImpl.class
			.getName());

	/**
	 * 
	 */
	@Override
	public AuthUser findUserByUsername(String username) {
		logger.entry(username);

		AuthUser user = null;

		try {

			user = getEntityManager()
					.createQuery(
							"select u from AuthUser u "
									+ " left join fetch u.groups "
									+ " where u.username = :username",
							AuthUser.class).setParameter("username", username)
					.getSingleResult();
		} catch (RuntimeException re) {
			logger.throwing(re);
			throw new AppDAOException("User with username: " + username
					+ " does not exist in the database", re);
		}

		return user;
	}

	@Override
	public void addGroupMembership(AuthUser user, String group) {
		// TODO Auto-generated method stub

	}

	/*
	 * This code SUCKS! may not scale in the case of hundreds of users. We are
	 * hitting the DB way too many times. The Query should be changed to bring
	 * back all the authorities along with corresponding users in one shot. KM
	 * 
	 * We take care of both group and custom authorities here (non-Javadoc)
	 * 
	 * @see com.hurontg.focus.persistence.AuthUserDAO#loadGroupAuthorities
	 * (java.lang.String)
	 */
	@Override
	public List<Authority> loadGroupAuthorities(String username) {
		logger.entry();

		List<Authority> groupAuthorities = new ArrayList<Authority>();

		try {
			AuthUser user = getEntityManager()
					.createQuery(
							"select u from AuthUser u  "
									+ " left join fetch u.groups g "
									+ " left join fetch g.authorities"
									+ " left join fetch u.customAuthorities "
									+ " where u.username = :username",
							AuthUser.class).setParameter("username", username)
					.getSingleResult();

			// Group Authorities
			for (Group group : user.getGroups()) {
				for (Authority authority : group.getAuthorities()) {
					groupAuthorities.add(authority);
				}
			}

			// Custom Authorities
			for (Authority authority : user.getCustomAuthorities()) {
				logger.debug("name: " + username + "has authority: "
						+ authority.getAuthority());
				groupAuthorities.add(authority);
			}
		} catch (RuntimeException re) {
			throw new AppDAOException(
					"Error(s) encountered trying to load group authorities", re);
		}

		logger.exit();

		return groupAuthorities;
	}
	
	@Override
	public List<AuthUser> loadMembers() {
		logger.entry();
		List<AuthUser> users = null;
		try {
			users = getEntityManager()
					.createQuery(
							"select a from AuthUser a "
									+ " left join a.groups g "
									+ " where g.name = :members"									
									+ " order by a.lastName, a.firstName",
							AuthUser.class)
					.setParameter("members",
							Group.GROUPNAME_MEMBERS)
					.getResultList();
		} catch (RuntimeException re) {
			throw new AppDAOException(re);
		}

		logger.exit();
		return users;
	}
}
