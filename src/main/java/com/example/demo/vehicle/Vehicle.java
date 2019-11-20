package com.example.demo.vehicle;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="Booking_service")
public class Vehicle {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    private java.util.UUID booking_id;
    private String vehicle_id ;
    private String user_id ; // ????????
    private int initial_meter_reading;  //  from asset
    private int final_meter_reading;   // manual
    //@CreationTimestamp
    private Timestamp starttime;
    //@UpdateTimestamp
    private Timestamp endtime;
    private Timestamp pause_start_time;
    private Timestamp pause_end_time;

    private String start_station;
    private String end_station;
    private String vehicle_status;
    private long duration;
    private String status_of_ride;
    private long pause_duration;

}
