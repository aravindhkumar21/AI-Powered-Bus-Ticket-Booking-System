package com.project.BusTicketBooking.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BusTicketBooking.dto.booking.BookingRequestDTO;
import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.dto.booking.BookingUpdateDTO;
import com.project.BusTicketBooking.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
@Validated
public class BookingController {

	private final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	// Create Booking
	@PostMapping("/book")
	public ResponseEntity<BookingResponseDTO> saveBooking(@Valid @RequestBody BookingRequestDTO dto) {
		BookingResponseDTO savedBooking = bookingService.saveBooking(dto);
		return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
	}

	// Get All Bookings
	@GetMapping("/allbookings")
	public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
		return ResponseEntity.ok(bookingService.getAllBookings());
	}
	
	// Get Bookings By User Id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(
	        @PathVariable Long userId) {

	    return ResponseEntity.ok(
	            bookingService.getBookingsByUserId(userId)
	    );
	}

	// Get Booking By Id
	@GetMapping("/booking/{id}")
	public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long id) {

		return ResponseEntity.ok(bookingService.getBookingById(id));
	}

	// Update Booking
	@PutMapping("/update-booking/{id}")
	public ResponseEntity<BookingResponseDTO> updateBooking(@PathVariable Long id,
			@Valid @RequestBody BookingUpdateDTO dto) {
		return ResponseEntity.ok(bookingService.updateBooking(id, dto));
	}

	// Delete Booking
	@DeleteMapping("/delete-booking/{id}")
	public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return ResponseEntity.ok("Booking deleted successfully.");
	}
	
	// Cancel Booking By User
	@DeleteMapping("/user/{userId}/{bookingId}")
	public ResponseEntity<String> cancelBookingByUser(
	        @PathVariable Long userId,
	        @PathVariable Long bookingId) {

	    bookingService.cancelBookingByUser(bookingId, userId);

	    return ResponseEntity.ok("Booking cancelled successfully.");
	}
}
