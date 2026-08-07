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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BusTicketBooking.dto.bus.BusRequestDTO;
import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.service.BusService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/buses")
public class BusController {

	private final BusService busService;

	public BusController(BusService busService) {
		this.busService = busService;
	}

	// Save Bus
	@PostMapping("/register-bus")
	public ResponseEntity<BusResponseDTO> saveBus(@Valid @RequestBody BusRequestDTO dto) {

		BusResponseDTO savedBus = busService.saveBus(dto);

		return new ResponseEntity<>(savedBus, HttpStatus.CREATED);
	}

	// Get All Buses
	@GetMapping("/allbuses")
	public ResponseEntity<List<BusResponseDTO>> getAllBuses() {

		List<BusResponseDTO> buses = busService.getAllBuses();

		return ResponseEntity.ok(buses);
	}

	// Get Bus By Id
	@GetMapping("/bus/{id}")
	public ResponseEntity<BusResponseDTO> getBusById(@PathVariable Long id) {

		BusResponseDTO bus = busService.getBusById(id);

		return ResponseEntity.ok(bus);
	}

	// Update Bus
	@PutMapping("/update-bus/{id}")
	public ResponseEntity<BusResponseDTO> updateBus(@PathVariable Long id, @Valid @RequestBody BusRequestDTO dto) {

		BusResponseDTO updatedBus = busService.updateBus(id, dto);

		return ResponseEntity.ok(updatedBus);
	}

	// Delete Bus
	@DeleteMapping("/delete-bus/{id}")
	public ResponseEntity<String> deleteBus(@PathVariable Long id) {

		busService.deleteBus(id);

		return ResponseEntity.ok("Bus deactivated successfully.");
	}

	@GetMapping("/search")
	public ResponseEntity<List<BusResponseDTO>> searchBuses(@RequestParam String source, @RequestParam String destination) {

		return ResponseEntity.ok(busService.searchBuses(source, destination));
	}

}
