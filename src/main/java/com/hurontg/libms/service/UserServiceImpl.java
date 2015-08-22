package com.hurontg.libms.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Service;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.AuthUser;
import com.hurontg.libms.persistence.AuthUserDAO;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory.getXLogger(UserServiceImpl.class
			.getName());

	/**
	 * 
	 */
	@Inject
	private AuthUserDAO userDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.med.dc.authmgr.service.UserService#loadUsers()
	 */
	@Override
	public List<AuthUser> loadMembers() {
		logger.entry();

		List<AuthUser> users = null;
		try {
			users = userDao.loadMembers();
		} catch (AppDAOException e) {
			throw new AppServiceException(e);
		}

		logger.exit();
		return users;
	}

}