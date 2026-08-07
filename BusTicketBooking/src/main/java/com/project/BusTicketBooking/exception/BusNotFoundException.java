package com.project.BusTicketBooking.exception;

public class BusNotFoundException extends RuntimeException {

    public BusNotFoundException(String message) {
        super(message);
    }

}
