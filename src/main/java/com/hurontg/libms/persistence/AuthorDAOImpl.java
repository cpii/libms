package com.hurontg.libms.persistence;

import java.util.List;

import javax.persistence.TypedQuery;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.stereotype.Repository;

import com.hurontg.common.persistence.AbstractGenericDAOImpl;
import com.hurontg.libms.domain.Author;

@Repository
public class AuthorDAOImpl extends AbstractGenericDAOImpl<Author> implements
		AuthorDAO {
	private XLogger logger = XLoggerFactory.getXLogger(AuthorDAOImpl.class
			.getName());

	@Override
	public List<Author> getAll() {
		TypedQuery<Author> query = getEntityManager().createQuery(
				"select a from Author a order by a.lastName", Author.class); // JPQL
		return query.getResultList();
	}

}
