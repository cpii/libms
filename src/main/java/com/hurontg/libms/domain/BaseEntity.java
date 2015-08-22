package com.hurontg.libms.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.hurontg.common.persistence.Auditor;

@MappedSuperclass
@EntityListeners(Auditor.class)
public class BaseEntity {

	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private Boolean deleted = false;
	private Long optLock;

	public BaseEntity() {
		deleted = false;
		optLock = 0L;
	}

	/**
	 * @return the createdBy
	 */
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn
	 *            the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedBy
	 */
	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedOn
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn
	 *            the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the optLock
	 */
	@Version
	@Column(name = "OPTLOCK")
	public Long getOptLock() {
		return optLock;
	}

	/**
	 * @param optLock
	 *            the optLock to set
	 */
	public void setOptLock(Long optLock) {
		this.optLock = optLock;
	}

	/**
	 * @return the deleted
	 */
	@Column(name = "DELETED", columnDefinition = "BIT")
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
