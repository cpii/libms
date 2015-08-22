package com.hurontg.libms.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.Publisher;

@Repository
public class PublisherDAOImpl extends AbstractGenericDAOImpl<Publisher>
		implements PublisherDAO {
	private XLogger logger = XLoggerFactory.getXLogger(PublisherDAOImpl.class
			.getName());

	@Override
	public List<Publisher> getAll() {
		TypedQuery<Publisher> query = getEntityManager().createQuery(
				"select p from Publisher p order by p.name", Publisher.class); // JPQL
		return query.getResultList();
	}

}
