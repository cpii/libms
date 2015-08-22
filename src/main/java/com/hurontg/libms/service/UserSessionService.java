package com.hurontg.libms.service;

import com.hurontg.libms.domain.UserSession;

public interface UserSessionService {

	public UserSession saveLogin(UserSession us);

	public void saveLogout(UserSession us);

}
