package com.pluralsight.repository;

import com.pluralsight.model.Ride;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("RideRepositoryImpl_simpleJDBC")
public class RideRepositoryImpl_simpleJDBC implements RideRepository {
    public RideRepositoryImpl_simpleJDBC () {System.out.println("RideRepositoryImpl_simpleJDBC");}

    @Override
    public List<Ride> getRides() {
        return null;
    }

    @Override
    public Ride createRide(Ride ride) {
        return null;
    }
}
