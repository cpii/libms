package com.hurontg.libms.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hurontg.libms.domain.Car;
import com.hurontg.libms.persistence.CarDAO;

@Service
public class CarServiceImpl implements CarService {
	@Inject
	private CarDAO carDao;

	@Override
	public List<Car> findAllCars() {
		List<Car> cars = carDao.retrieveAllCars();
		return cars;
	}

}
