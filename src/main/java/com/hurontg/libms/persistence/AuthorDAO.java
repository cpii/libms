package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.Author;

// Data Access Object
public interface AuthorDAO extends GenericDAO<Author> {
	List<Author> getAll();
}
