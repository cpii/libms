package com.hurontg.libms.persistence;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.UserSession;

@Repository
public class UserSessionDAOImpl extends AbstractGenericDAOImpl<UserSession>
		implements UserSessionDAO {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private XLogger logger = XLoggerFactory.getXLogger(UserSessionDAOImpl.class
			.getName());

}
