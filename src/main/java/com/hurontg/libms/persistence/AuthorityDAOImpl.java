package com.hurontg.libms.persistence;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.Authority;

@Repository
public class AuthorityDAOImpl extends AbstractGenericDAOImpl<Authority>
		implements AuthorityDAO {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(AuthorityDAOImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hurontg.focus.persistence.AuthUserDAO#
	 * getAuthorityIdByAuthorityName(java.lang.String)
	 */
	@Override
	public Long getAuthorityIdByAuthorityName(String auth) {
		logger.entry();

		Long id = null;

		try {
			id = (Long) getEntityManager()
					.createQuery(
							"select a.id from Authority a"
									+ " where a.authority = :auth")
					.setParameter("auth", auth).getSingleResult();
		} catch (RuntimeException re) {
			throw new AppDAOException(
					"Failed to retrieve Authority ID by name", re);
		}

		logger.exit();

		return id;
	}

	@Override
	public Authority getAuthorityByAuthorityName(String auth) {
		logger.entry();

		Authority authority = null;

		try {
			authority = getEntityManager()
					.createQuery(
							"select a from Authority a"
									+ " where a.authority = :auth",
							Authority.class).setParameter("auth", auth)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve Authority by name",
					re);
		}

		logger.exit();

		return authority;
	}

}
