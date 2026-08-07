package com.project.BusTicketBooking.exception;

public class NoBusFoundException extends RuntimeException {
    public NoBusFoundException(String message) {
        super(message);
    }
}
