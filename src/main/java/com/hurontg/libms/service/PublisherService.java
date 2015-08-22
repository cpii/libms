package com.hurontg.libms.service;

import java.util.List;

import com.hurontg.libms.domain.Publisher;

public interface PublisherService {
	List<Publisher> findAll();

	void create(Publisher publisher);

	Publisher findById(Long id);

	Publisher update(Publisher publisher);

	void delete(Long id);

}
