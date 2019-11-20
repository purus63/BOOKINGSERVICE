package com.example.demo.service;

import com.example.demo.repository.Booking_Repository;
import com.example.demo.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

@Service
public class VehicleService {

    Booking_Repository repository;

    @Autowired
    public VehicleService(Booking_Repository booking_repository){
        this.repository = booking_repository;               // at runtime spring will provide this service a userRepository object via constructor dependency injection
    }

    Random rand = new Random();
    Vehicle new_vehicle = new Vehicle();

    public Vehicle StartRide()
    {
        new_vehicle.setUser_id("UserA");
        new_vehicle.setVehicle_id("Vehicle1");
        new_vehicle.setStart_station("First Station");
        new_vehicle.setInitial_meter_reading(rand.nextInt(50));
        new_vehicle.setStarttime(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setVehicle_status("Booked");
        new_vehicle.setStatus_of_ride("ONGOING");
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;
    }

    public Vehicle PauseRide()
    {
        new_vehicle.setPause_start_time(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setStatus_of_ride("PAUSED");
        new_vehicle.setPause_end_time(null);
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;
    }
    public Vehicle RestartRide()
    {
        new_vehicle.setPause_end_time(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setPause_duration((new_vehicle.getPause_duration() + new_vehicle.getPause_end_time().getTime()-new_vehicle.getPause_start_time().getTime())/1000);
        new_vehicle.setStatus_of_ride("ONGOING");
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;
    }
    public Vehicle EndRide()
    {
//        Optional<Vehicle> vehicle = repository.findById(entity.getBooking_id().toString());
//        Vehicle new_vehicle = vehicle.get();
        new_vehicle.setFinal_meter_reading(new_vehicle.getInitial_meter_reading()+rand.nextInt(100));
        new_vehicle.setStatus_of_ride("COMPLETED");
        new_vehicle.setEnd_station("Last Station");
        new_vehicle.setEndtime(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setVehicle_status("Available");
        new_vehicle.setDuration((new_vehicle.getEndtime().getTime()-new_vehicle.getStarttime().getTime())/1000);
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;    }
}
