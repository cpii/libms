package com.hurontg.libms.service;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.exception.AppServiceException;
import com.hurontg.libms.domain.UserSession;
import com.hurontg.libms.persistence.UserSessionDAO;

@Service
public class UserSessionServiceImpl implements UserSessionService {
	/**
	 * 
	 */
	private XLogger logger = XLoggerFactory
			.getXLogger(UserSessionServiceImpl.class.getName());

	/**
	 * 
	 */
	private UserSessionDAO dao;

	/**
	 * 
	 * @param dao
	 */
	@Inject
	public void setDao(UserSessionDAO dao) {
		this.dao = dao;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public UserSession saveLogin(UserSession us) {
		logger.entry(us);

		try {
			dao.makePersistent(us);
		} catch (AppDAOException wde) {
			throw new AppServiceException("Cannot Save Login", wde);
		}

		logger.exit(us);

		return us;
	}

	/**
	 * 
	 * @param dto
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveLogout(UserSession us) {
		logger.entry(us);

		try {

			us = dao.findById(us.getId(), false);
			us.setLogoutDate(new Date());

			dao.mergeState(us, false);

		} catch (AppDAOException wde) {
			throw new AppServiceException("Cannot Save Logout", wde);
		}

		logger.exit();
	}

}
