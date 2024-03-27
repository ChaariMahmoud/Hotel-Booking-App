package com.dailycodework.marinahotel.exception;

public class InvalidBookingRequestException extends RuntimeException{
    public  InvalidBookingRequestException (String message){
        super(message);
    }
}
