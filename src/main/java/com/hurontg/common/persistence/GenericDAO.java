package com.hurontg.common.persistence;

/**
 * Generic DAO interface.
 * 
 * @param <T>
 *            the entity type
 * @param <ID>
 *            the primary key type
 */
public interface GenericDAO<T> {
	/**
	 * Find an entity by its primary key.
	 * 
	 * @param identifier
	 *            the primary key.
	 * @param lock
	 *            locks the object.
	 * @return the entity.
	 */
	T findById(Long identifier, boolean lock);

	/**
	 * Persists entity.
	 * 
	 * @param entity
	 *            entity.
	 * @return the entity.
	 */
	T makePersistent(T entity);

	/**
	 * Removes an entity.
	 * 
	 * @param entity
	 *            entity.
	 */
	void makeTransient(T entity);

	/**
	 * Merges the entity state.
	 * 
	 * @param entity
	 *            entity.
	 * @param flush
	 *            if true, forces Hibernate's persistence context to synchronise
	 *            state with the database
	 * @return the entity.
	 */
	T mergeState(T entity, boolean flush);

	/**
	 * Detaches the entity from the Entity Manager
	 * 
	 * @param entity
	 */
	void detach(T entity);

	/**
	 * Flushes the manager.
	 */
	void flush();

	/**
	 * Clears the manager.
	 */
	void clear();

	/**
	 * Returns a true if the value exists in the DB.
	 * 
	 * @param entityClassName
	 *            , (Faculty, for example)
	 * @param field
	 *            , (cpsoNumber, for example)
	 * @param value
	 * @return
	 */
	boolean isFiledValueUnique(String entityClassName, String field,
			Object value);
}
