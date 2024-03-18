package com.dailycodework.marinahotel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BookingId ;

    @Column (name = "check_In")
    private LocalDate checkInDate ;


    @Column (name = "check_Out")
    private LocalDate getCheckInDateDate ;

    @Column (name = "guest_FullName")
    private String guestFullName ;

    @Column (name = "guest_Email")
    private String guestEmail ;

    @Column (name = "adults")
    private int NumOfAdults ;

    @Column (name = "children")
        private int NumOfChildren ;

    @Column (name = "total_guest")
    private int totalNumOfGuest ;

    @Column (name = "confirmtion_Code")
    private String bookingConformationCode ;


    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn (name = "room_id")
    private Room room ;

    public void calculateTotalNumberOfGuest (){
        this.totalNumOfGuest =this.NumOfAdults +this.NumOfChildren ;

    }

    public void setNumOfAdults (int numOfAdults){
        NumOfAdults =numOfAdults ;
        calculateTotalNumberOfGuest ();
    }

    public void  setNumOfChildren (int numOfChildren){
        NumOfChildren = numOfChildren ;
        calculateTotalNumberOfGuest ();
    }

    public void setBookingConformationCode (String bookingConformationCode){
        this.bookingConformationCode =bookingConformationCode ;
    }



}
