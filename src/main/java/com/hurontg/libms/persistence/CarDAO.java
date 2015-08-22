package com.hurontg.libms.persistence;

import java.util.List;

import com.hurontg.libms.domain.Car;

public interface CarDAO {
	Car findById(final Long identifier);

	Car saveCar(final Car entity);

	void deleteCar(final Car entity);

	Car mergeState(Car entity);

	List<Car> retrieveAllCars();
}
