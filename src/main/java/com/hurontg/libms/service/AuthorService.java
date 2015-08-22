package com.hurontg.libms.service;

import java.util.List;

import com.hurontg.libms.domain.Author;

public interface AuthorService {
	List<Author> findAll();

	void create(Author author);

	Author findById(Long id);

	Author update(Author author);

	void delete(Long id);

}
