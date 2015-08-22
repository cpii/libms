package com.hurontg.libms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hurontg.libms.domain.Publisher;
import com.hurontg.libms.persistence.PublisherDAO;

@Service
public class PublisherServiceImpl implements PublisherService {
	@Inject
	PublisherDAO pubDao;

	@Override
	public List<Publisher> findAll() {
		return pubDao.getAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Publisher publisher) {
		pubDao.makePersistent(publisher);
	}

	@Override
	public Publisher findById(Long id) {
		return pubDao.findById(id, false);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
		Publisher publisher = pubDao.findById(id, false);
		pubDao.makeTransient(publisher);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Publisher update(Publisher publisher) {
		Publisher pub = pubDao.findById(publisher.getId(), false);
		pub.setEmail(publisher.getEmail());
		pub.setPhone(publisher.getPhone());
		pub.setName(publisher.getName());
		return pubDao.mergeState(pub, false);
	}

}
