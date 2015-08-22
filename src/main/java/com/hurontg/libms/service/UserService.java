package com.hurontg.libms.service;

import java.util.List;

import com.hurontg.libms.domain.AuthUser;

public interface UserService {
	public List<AuthUser> loadMembers();
}
