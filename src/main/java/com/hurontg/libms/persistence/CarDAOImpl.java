package com.hurontg.libms.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hurontg.libms.domain.Car;

@Repository
public class CarDAOImpl implements CarDAO {
	private EntityManager em;

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
	 * @param identifier
	 *            id.
	 * @param lock
	 *            lock.
	 * @return .
	 */
	public final Car findById(final Long identifier) {
		Car entity;
		entity = (Car) em.find(Car.class, identifier);
		return entity;
	}

	/**
	 * @param entity
	 *            entity.
	 * @return .
	 */
	public final Car saveCar(final Car entity) {
		em.persist(entity);
		return entity;
	}

	/**
	 * Deletes the DB row
	 * 
	 * @param entity
	 */
	public final void deleteCar(final Car entity) {
		em.remove(entity);
	}

	/**
	 * 
	 */
	public Car mergeState(Car entity) {
		Car car = em.merge(entity);
		return car;
	}

	@Override
	public List<Car> retrieveAllCars() {
		List<Car> cars = em.createQuery("select c from Car c", Car.class)
				.getResultList();
		return cars;
	}
}
