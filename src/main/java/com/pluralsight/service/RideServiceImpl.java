package com.pluralsight.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;

@Service("rideService")
public class RideServiceImpl implements RideService {

/*	@Autowired*/
	private RideRepository rideRepository;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public RideServiceImpl (@Qualifier("MyRepositoryAlias") RideRepository rideRepository) {
		this.rideRepository = rideRepository;
		logger.info("RideService is created.");
		logger.debug("RideService is using {}", rideRepository.getClass().getName());
	}
	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}

	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}
}
