package com.project.BusTicketBooking.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({
	    UserNotFoundException.class,
	    BusNotFoundException.class,
	    RouteNotFoundException.class,
	    SeatNotFoundException.class,
	    BookingNotFoundException.class,
	    PaymentNotFoundException.class,
	    AdminNotFoundException.class,
	    PaymentAlreadyExistsException.class,
	    BusDeletionException.class,
	    BusTypeModificationException.class,
	    BusInactiveException.class,
	    SeatAlreadyBookedException.class,
	    SeatNotBelongToBusException.class,
	    NoBusFoundException.class
	})
	public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException ex) {

	    ErrorResponse error = new ErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.NOT_FOUND.value(),
	            "NOT FOUND",
	            ex.getMessage());

	    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error ->
	        errors.put(error.getField(), error.getDefaultMessage())
	    );

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {

	    ErrorResponse error = new ErrorResponse(
	            LocalDateTime.now(),
	            HttpStatus.INTERNAL_SERVER_ERROR.value(),
	            "INTERNAL SERVER ERROR",
	            ex.getMessage());

	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
