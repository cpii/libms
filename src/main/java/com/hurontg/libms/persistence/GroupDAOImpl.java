package com.hurontg.libms.persistence;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.exception.AppDAOException;
import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.Group;

@Repository
public class GroupDAOImpl extends AbstractGenericDAOImpl<Group> implements
		GroupDAO {
	private XLogger logger = XLoggerFactory.getXLogger(GroupDAOImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hurontg.focus.persistence.GroupDAO#findByName(java.lang.String )
	 */
	@Override
	public Group findByName(String name) {
		logger.entry();

		Group group = null;
		try {
			group = getEntityManager()
					.createQuery(
							"select g from Group g" + " where g.name = :name",
							Group.class).setParameter("name", name)
					.getSingleResult();
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to retrieve Group by name", re);
		}

		logger.exit();
		return group;
	}

}
