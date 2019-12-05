package com.example.demo.repository;

import com.example.demo.vehicle.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;


public interface Booking_Repository extends JpaRepository<Vehicle,String> {

//    Page<Vehicle> findAll(Pageable pageable);

    @Query("FROM Vehicle m where m.booking_id = ?1")
    public List<Vehicle> findByBooking_id( UUID id);
}
