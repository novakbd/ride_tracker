package com.pluralsight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;

@Service("rideService")
public class RideServiceImpl implements RideService {

/*	@Autowired*/
	private RideRepository rideRepository;
	@Autowired
	public RideServiceImpl (@Qualifier("MyRepositoryAlias") RideRepository rideRepository) {
		this.rideRepository = rideRepository;
		System.out.println(rideRepository.getClass().getName());
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
