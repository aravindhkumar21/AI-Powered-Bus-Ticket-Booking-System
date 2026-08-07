package com.project.BusTicketBooking.exception;

public class BusInactiveException extends RuntimeException {

    public BusInactiveException(String message) {
        super(message);
    }
}