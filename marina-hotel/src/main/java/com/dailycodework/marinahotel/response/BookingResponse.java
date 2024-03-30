package com.dailycodework.marinahotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
    private Long BookingId ;
    private LocalDate checkInDate ;
    private LocalDate CheckOutDate ;
    private String guestFullName ;
    private String guestEmail ;
    private int NumOfAdults ;
    private int NumOfChildren ;
    private int totalNumOfGuest ;
    private String bookingConformationCode ;
    private RoomResponse room;


    public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate CheckOutDate,
                           String bookingConformationCode ) {
        BookingId = bookingId;
        this.checkInDate = checkInDate;
        this.CheckOutDate = CheckOutDate;
        this.bookingConformationCode = bookingConformationCode;


    }
}
