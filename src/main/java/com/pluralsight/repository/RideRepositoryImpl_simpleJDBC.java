package com.pluralsight.repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.ExceptionHandler;
import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Repository("RideRepositoryImpl_simpleJDBC")
public class RideRepositoryImpl_simpleJDBC implements RideRepository {

    private DataSource dataSource;

    @Autowired
    public RideRepositoryImpl_simpleJDBC (DataSource dataSource) {
        this.dataSource = dataSource;
        /*this.url = "jdbc:mysql://localhost:3306/ride_tracker?user=app_user&password=password&serverTimezone=" + TimeZone.getDefault().getID();*/
        System.out.println("RideRepositoryImpl_simpleJDBC");
    }

    @Override
    public List<Ride> getRides() {
        String query = "SELECT * FROM ride";
        List<Ride> rides = new ArrayList<>();

        try (/*Connection connection = DriverManager.getConnection(this.url);*/
             Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ride ride = new Ride();
                ride.setId(resultSet.getInt("id"));
                ride.setName(resultSet.getString("name"));
                ride.setDuration(resultSet.getInt("duration"));
                rides.add(ride);
            }

        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
        return rides;
    }

    @Override
    public Ride createRide(Ride ride) {
        String query = "INSERT INTO ride (name, duration) VALUES (?,?)";
        Ride createdRide = null;
        try (/*Connection connection = DriverManager.getConnection(this.url);*/
             Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"id"});) {

            preparedStatement.setString(1, ride.getName());
            preparedStatement.setInt(2, ride.getDuration());

            Integer rowCount = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                Integer autoIncKeyFromApi = resultSet.getInt(1);
                createdRide = this.getRide(autoIncKeyFromApi);
            } else {
                throw new Exception("Couldn't insert ride: " + ride.getName());
            }

        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
        return createdRide;
    }

    private Ride getRide(Integer id) {

        String query = "SELECT * FROM ride WHERE id=?";
        Ride ride = new Ride();
        try (/*Connection connection = DriverManager.getConnection(this.url);*/
             Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ride.setId(resultSet.getInt("id"));
                ride.setName(resultSet.getString("name"));
                ride.setDuration(resultSet.getInt("duration"));
            }
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
        return ride;
    }
}
