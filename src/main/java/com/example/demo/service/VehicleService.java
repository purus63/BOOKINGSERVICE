package com.example.demo.service;

import com.example.demo.Exceptions.VehicleNotFoundException;
import com.example.demo.repository.Booking_Repository;
import com.example.demo.vehicle.Vehicle;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class VehicleService implements Booking_Service {

    Booking_Repository repository;


//    private Timestamp pause_start_time=null;
//    private Timestamp pause_end_time=null;
    @Autowired
    public VehicleService(Booking_Repository booking_repository){
        this.repository = booking_repository;               // at runtime spring will provide this service a userRepository object via constructor dependency injection
    }

    Random rand = new Random();
    Vehicle new_vehicle;
    public Vehicle StartRide(Vehicle vehicle)
    {
	new_vehicle = new Vehicle();
        new_vehicle.setUser_id(vehicle.getUser_id());
        new_vehicle.setVehicle_id(vehicle.getVehicle_id());
        new_vehicle.setStart_station(vehicle.getStart_station());
        new_vehicle.setEnd_station(vehicle.getEnd_station());
        new_vehicle.setInitial_meter_reading(vehicle.getInitial_meter_reading());

        new_vehicle.setStarttime(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setVehicle_status("Booked");
        new_vehicle.setStatus_of_ride("ONGOING");
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;
    }

//    public Vehicle PauseRide()
//    {
//        pause_start_time= new Timestamp(System.currentTimeMillis());
//        new_vehicle.setStatus_of_ride("PAUSED");
//        pause_end_time=null;
//        new_vehicle = repository.save(new_vehicle);
//        return new_vehicle;
//    }
//    public Vehicle RestartRide()
//    {
//        pause_end_time = new Timestamp(System.currentTimeMillis());
//        new_vehicle.setPause_duration((new_vehicle.getPause_duration() + pause_end_time.getTime()-pause_start_time.getTime())/1000);
//        new_vehicle.setStatus_of_ride("ONGOING");
//        new_vehicle = repository.save(new_vehicle);
//        return new_vehicle;
//    }
    public Vehicle EndRide(Vehicle vehicle)
    {
//        Optional<Vehicle> vehicle = repository.findById(entity.getBooking_id().toString());
//        Vehicle new_vehicle = vehicle.get();
        new_vehicle.setFinal_meter_reading(vehicle.getFinal_meter_reading());
        new_vehicle.setStatus_of_ride("COMPLETED");
        new_vehicle.setEndtime(new Timestamp(System.currentTimeMillis()));
        new_vehicle.setVehicle_status("Available");
        new_vehicle.setDuration((new_vehicle.getEndtime().getTime()-new_vehicle.getStarttime().getTime())/1000);
        new_vehicle.setFare((new_vehicle.getFinal_meter_reading()-new_vehicle.getInitial_meter_reading())*5 +(double)(new_vehicle.getPause_duration()* 0.0016) + (double)((new_vehicle.getDuration()-new_vehicle.getPause_duration())*.0032));
        new_vehicle = repository.save(new_vehicle);
        return new_vehicle;    }



    @Override
    public List<Vehicle> getAllVehicle(Integer pageLimit, Integer pageNum) throws VehicleNotFoundException {
        if (pageLimit == null) {
            pageLimit = 10;
        }
        if (pageNum == null) {
            pageNum = 0;
        }
        List<Vehicle> allVehicle =  repository.findAll(PageRequest.of(pageNum, pageLimit)).getContent();
//        List<Ticket> allTickets = ticketRepository.findAll();
        if (allVehicle.size() == 0) {
            throw new VehicleNotFoundException("No vehicle available!");
        }
        return allVehicle;
    }

    @Override
    public List<Vehicle> findByBooking_id(UUID x) throws VehicleNotFoundException {


        List<Vehicle> vehicleList = repository.findByBooking_id(x);
        if(vehicleList.size()==0)
            throw new VehicleNotFoundException("No vehicle with this booking_id");
        return vehicleList;
    }
}
