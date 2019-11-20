package com.example.demo.repository;

import com.example.demo.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Booking_Repository extends JpaRepository<Vehicle,String> {
}
