package com.project.BusTicketBooking.exception;

public class SeatNotBelongToBusException extends RuntimeException{

	public SeatNotBelongToBusException(String msg) {
		super(msg);
	}
}
