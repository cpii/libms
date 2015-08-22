package com.hurontg.libms.persistence;

import com.hurontg.common.persistence.GenericDAO;
import com.hurontg.libms.domain.Authority;

public interface AuthorityDAO extends GenericDAO<Authority> {
	public Long getAuthorityIdByAuthorityName(String auth);

	public Authority getAuthorityByAuthorityName(String auth);
}
