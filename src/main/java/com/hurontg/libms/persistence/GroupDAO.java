package com.hurontg.libms.persistence;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.Group;

public interface GroupDAO extends GenericDAO<Group> {
	public Group findByName(String name);
}
