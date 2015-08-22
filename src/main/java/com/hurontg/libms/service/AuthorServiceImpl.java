package com.hurontg.libms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hurontg.libms.domain.Author;
import com.hurontg.libms.persistence.AuthorDAO;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Inject
	AuthorDAO authDao;

	@Override
	public List<Author> findAll() {
		return authDao.getAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Author author) {
		authDao.makePersistent(author);
	}

	@Override
	public Author findById(Long id) {
		return authDao.findById(id, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		Author author = authDao.findById(id, false);
		authDao.makeTransient(author);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Author update(Author author) {
		Author a = authDao.findById(author.getId(), false);
		a.setBirthday(author.getBirthday());
		a.setFirstName(author.getFirstName());
		a.setMiddleName(author.getMiddleName());
		a.setLastName(author.getLastName());
		a.setGender(author.getGender());

		authDao.mergeState(a, false);
		return a;
	}

}
