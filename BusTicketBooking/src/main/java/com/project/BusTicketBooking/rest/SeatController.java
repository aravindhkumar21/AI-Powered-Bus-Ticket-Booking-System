package com.project.BusTicketBooking.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BusTicketBooking.dto.seat.SeatRequestDTO;
import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;
import com.project.BusTicketBooking.model.Seat;
import com.project.BusTicketBooking.service.SeatService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/seats")
public class SeatController {

	private final SeatService seatService;

	public SeatController(SeatService seatService) {
		this.seatService = seatService;
	}

	//Get seats by bus
	@GetMapping("/bus/{busId}")
	public ResponseEntity<List<SeatResponseDTO>> getSeatsByBusId(@PathVariable Long busId) {

		return ResponseEntity.ok(seatService.getSeatsByBusId(busId));
	}

	// Get All Seats
	@GetMapping("/allseats")
	public ResponseEntity<List<SeatResponseDTO>> getAllSeats() {

		List<SeatResponseDTO> seats = seatService.getAllSeats();

		return ResponseEntity.ok(seats);
	}

	// Get Seat By Id
	@GetMapping("/seat/{id}")
	public ResponseEntity<SeatResponseDTO> getSeatById(@PathVariable Long id) {

		SeatResponseDTO seat = seatService.getSeatById(id);

		return ResponseEntity.ok(seat);
	}

	// Update Seat
	@PutMapping("/update-seat/{id}")
	public ResponseEntity<SeatResponseDTO> updateSeat(@PathVariable Long id, @Valid @RequestBody SeatRequestDTO dto) {

		SeatResponseDTO updatedSeat = seatService.updateSeat(id, dto);

		return ResponseEntity.ok(updatedSeat);
	}

	
}
