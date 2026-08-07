package com.project.BusTicketBooking.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message) {
        super(message);
    }

}
