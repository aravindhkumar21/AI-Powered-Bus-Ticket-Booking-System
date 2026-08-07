package com.project.BusTicketBooking.exception;

public class SeatNotFoundException extends RuntimeException {

    public SeatNotFoundException(String message) {
        super(message);
    }

}
