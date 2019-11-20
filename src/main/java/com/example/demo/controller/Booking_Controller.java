package com.example.demo.controller;

import com.example.demo.repository.Booking_Repository;
import com.example.demo.service.VehicleService;
import com.example.demo.vehicle.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


    @RestController
    @RequestMapping("/v1")
    public class Booking_Controller {

    @Autowired
    private VehicleService vehicle_service;

    ResponseEntity responseEntity;

    @GetMapping("start_ride")
    public ResponseEntity<?> register() {
        try {
            Vehicle start_ride = vehicle_service.StartRide();
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("pause_ride")
    public ResponseEntity<?> pause() {
        try {
            Vehicle pause_ride = vehicle_service.PauseRide();
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
        @GetMapping("restart_ride")
        public ResponseEntity<?> restart() {
            try {
                Vehicle restart_ride = vehicle_service.RestartRide();
                responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
            } catch (Exception ex) {
                responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
            }
            return responseEntity;
        }
    @GetMapping("end_ride")
    public ResponseEntity<?> end_ride() {

        try {
            Vehicle end_ride = vehicle_service.EndRide();
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}