package com.hurontg.common.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import com.hurontg.common.exception.AppDAOException;

/**
 * Implementation of <code>GenericDAO</code>.
 * 
 * @param <T>
 *            The type of the domain object for which this instance is to be
 *            used.
 * @param <ID>
 *            The type of the id of the domain object for which this instance is
 *            to be used.
 */
public abstract class AbstractGenericDAOImpl<T> implements GenericDAO<T> {
	/**
     *
     */
	private final transient Class<T> entityBeanType;

	/**
	 * @PersistenceContext will be set in the concrete sub-classes
	 */
	private EntityManager em;

	/**
     *
     */
	@SuppressWarnings("unchecked")
	public AbstractGenericDAOImpl() {
		this.entityBeanType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];		
	}

	/**
	 * @param entityManager
	 *            Entity Manager
	 */
	@PersistenceContext(unitName = "libmsPU")
	protected void setEntityManager(final EntityManager em) {
		this.em = em;
	}

	/**
	 * @return EntityManager.
	 */
	protected final EntityManager getEntityManager() {
		return em;
	}

	/**
	 * @return .
	 */
	public final Class<T> getEntityBeanType() {
		return entityBeanType;
	}

	/**
	 * @param identifier
	 *            id.
	 * @param lock
	 *            lock.
	 * @return .
	 */
	public final T findById(final Long identifier, final boolean lock) {
		T entity;

		try {
			if (lock) {
				entity = (T) getEntityManager().find(getEntityBeanType(),
						identifier);
				getEntityManager().lock(entity, LockModeType.WRITE);
			} else {
				entity = (T) getEntityManager().find(getEntityBeanType(),
						identifier);
			}
		} catch (RuntimeException re) {
			throw new AppDAOException("Cannot find entity", re);
		}

		return entity;
	}

	/**
	 * @param entity
	 *            entity.
	 * @return .
	 */
	public final T makePersistent(final T entity) {
		try {
			getEntityManager().persist(entity);
		} catch (EntityExistsException eee) {
			throw new AppDAOException("Attempt to insert duplicate entity", eee);
		} catch (PersistenceException pe) {
			if (pe.getCause()
					.getClass()
					.getName()
					.equalsIgnoreCase(
							"org.hibernate.exception.ConstraintViolationException")) {
				throw new AppDAOException(
						pe.getCause().getCause().getMessage(), pe,
						AppDAOException.UNIQUE_CONSTRAINT_VIOLATION);
			} else {
				throw new AppDAOException(pe);
			}
		} catch (RuntimeException re) {
			throw new AppDAOException(re);
		}
		return entity;
	}

	/**
	 * Deletes the DB row
	 * 
	 * @param entity
	 */
	public final void makeTransient(final T entity) {
		try {
			getEntityManager().remove(entity);
		} catch (RuntimeException re) {
			throw new AppDAOException("Failed to delete entity. "
					+ re.getMessage(), re);
		}
	}

	/**
     *
     */
	public final void flush() {
		try {
			getEntityManager().flush();
		} catch (PersistenceException pe) {
			if (pe.getCause()
					.getClass()
					.getName()
					.equalsIgnoreCase(
							"org.hibernate.exception.ConstraintViolationException")) {
				throw new AppDAOException(
						pe.getCause().getCause().getMessage(), pe,
						AppDAOException.UNIQUE_CONSTRAINT_VIOLATION);
			}
		} catch (RuntimeException re) {
			throw new AppDAOException(re);
		}
	}

	/**
     *
     */
	public final void clear() {
		getEntityManager().clear();
	}

	/**
	 * 
	 */
	public T mergeState(T entity, boolean flush) {
		try {
			T t = getEntityManager().merge(entity);
			if (flush) {
				flush();
			}

			return t;
		} catch (IllegalStateException ise) {
			throw new AppDAOException(ise);
		} catch (TransactionRequiredException tre) {
			throw new AppDAOException(tre);
		} catch (PersistenceException pe) {
			if (pe.getCause()
					.getClass()
					.getName()
					.equalsIgnoreCase(
							"org.hibernate.exception.ConstraintViolationException")) {
				throw new AppDAOException(
						pe.getCause().getCause().getMessage(), pe,
						AppDAOException.UNIQUE_CONSTRAINT_VIOLATION);
			} else {
				throw new AppDAOException(pe);
			}
		} catch (IllegalArgumentException ile) {
			throw new AppDAOException(ile);
		}
	}

	/**
	 * 
	 * @param entity
	 */
	public void detach(T entity) {
		try {
			getEntityManager().detach(entity);
		} catch (IllegalArgumentException iae) {
			throw new AppDAOException(iae);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hurontg.common.persistence.GenericDAO#isFiledValueUnique(java.
	 * lang.String, java.lang.Object)
	 */
	@Override
	public boolean isFiledValueUnique(String entityClassName, String field,
			Object value) {
		boolean unique = true;

		try {
			getEntityManager()
					.createQuery(
							"select 1 from " + entityClassName + " e where e."
									+ field + " = :fieldValue")
					.setParameter("fieldValue", value).getSingleResult();

			unique = false;
		} catch (NoResultException nre) {
			// do nothing
		} catch (RuntimeException re) {
			throw new AppDAOException(re);
		}

		return unique;
	}

}
