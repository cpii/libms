package com.hurontg.common.persistence;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.hurontg.common.util.Utils;
import com.hurontg.libms.domain.BaseEntity;

public class Auditor {
	@PrePersist
	public void prePersist(BaseEntity be) {
		be.setCreatedOn(new Date());
		be.setCreatedBy(Utils.getUsername());
	}
	
	@PreUpdate
	public void preUpdate(BaseEntity be) {
		be.setUpdatedOn(new Date());
		be.setUpdatedBy(Utils.getUsername());
	}
}
