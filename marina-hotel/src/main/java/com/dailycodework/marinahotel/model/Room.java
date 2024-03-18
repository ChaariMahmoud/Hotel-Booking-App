package com.dailycodework.marinahotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.yaml.snakeyaml.events.Event;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Entity
@Getter
@Setter
@AllArgsConstructor


public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id ;
    private  String roomType ;
    private BigDecimal roomPrice ;
    private boolean isBooked = false ;

    @Lob
    private Blob photo ;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookedRoom> bookings ;

    public Room(){
        this.bookings = new ArrayList<>();
    }

    public void addBooking (BookedRoom booking){
        if (booking == null){
            bookings = new ArrayList<>() ;
        }
        bookings.add(booking);
        booking.setRoom(this);
        isBooked =true ;
        String bookingCode = RandomStringUtils.randomNumeric(10);
        booking.setBookingConformationCode(bookingCode);

    }




}
