package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.Publisher;

// Data Access Object
public interface PublisherDAO extends GenericDAO<Publisher> {
	List<Publisher> getAll();
}
