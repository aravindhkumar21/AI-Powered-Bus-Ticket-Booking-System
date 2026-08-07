package com.project.BusTicketBooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.ai.BusSearchResponse;
import com.project.BusTicketBooking.dto.bus.BusRequestDTO;
import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.enums.BusType;
import com.project.BusTicketBooking.enums.SeatStatus;
import com.project.BusTicketBooking.enums.SeatType;
import com.project.BusTicketBooking.exception.AdminNotFoundException;
import com.project.BusTicketBooking.exception.BusInactiveException;
import com.project.BusTicketBooking.exception.BusNotFoundException;
import com.project.BusTicketBooking.exception.BusTypeModificationException;
import com.project.BusTicketBooking.exception.NoBusFoundException;
import com.project.BusTicketBooking.exception.RouteNotFoundException;
import com.project.BusTicketBooking.mapper.BusMapper;
import com.project.BusTicketBooking.model.Admin;
import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.model.Route;
import com.project.BusTicketBooking.model.Seat;
import com.project.BusTicketBooking.repo.AdminRepository;
import com.project.BusTicketBooking.repo.BusRepository;
import com.project.BusTicketBooking.repo.RouteRepository;
import com.project.BusTicketBooking.repo.SeatRepository;

import jakarta.transaction.Transactional;

@Service
public class BusServiceImpl implements BusService {

	private final BusRepository busRepo;
	private final AdminRepository adminRepo;
	private final RouteRepository routeRepo;
	private final SeatRepository seatRepo;
	private final RouteService routeService;

	public BusServiceImpl(BusRepository busRepo, AdminRepository adminRepo, RouteRepository routeRepo,
			SeatRepository seatRepo,RouteService routeService) {

		this.busRepo = busRepo;
		this.adminRepo = adminRepo;
		this.routeRepo = routeRepo;
		this.seatRepo = seatRepo;
		this.routeService=routeService;
	}

	@Override
	@Transactional
	public BusResponseDTO saveBus(BusRequestDTO dto) {

		Admin admin = adminRepo.findById(dto.getAdminId())
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id : " + dto.getAdminId()));

		Route route = routeRepo.findById(dto.getRouteId())
				.orElseThrow(() -> new RouteNotFoundException("Route not found with id : " + dto.getRouteId()));

		Bus bus = new Bus();

		bus.setBusName(dto.getBusName());

		bus.setBusNumber(dto.getBusNumber());

		bus.setBusType(dto.getBusType());

		bus.setFare(dto.getFare());

		bus.setTotalSeats(dto.getTotalSeats());

		bus.setAvailableSeats(dto.getTotalSeats());

		// Soft delete flag
		bus.setActive(true);

		bus.setAdmin(admin);

		bus.setRoute(route);

		Bus savedBus = busRepo.save(bus);

		// Automatically create seats
		createSeats(savedBus);

		return BusMapper.toResponseDTO(savedBus);
	}

	@Override
	public List<BusResponseDTO> getAllBuses() {

		return busRepo.findAll().stream().map(BusMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public BusResponseDTO getBusById(Long id) {

		Bus bus = busRepo.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id : " + id));

		return BusMapper.toResponseDTO(bus);
	}

	@Override
	@Transactional
	public BusResponseDTO updateBus(Long id, BusRequestDTO dto) {

		Bus bus = busRepo.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id : " + id));

		// Prevent updating inactive bus
		if (!bus.getActive()) {

			throw new BusInactiveException("Cannot update inactive bus.");
		}

		// Prevent bus type change after bookings
		if (!bus.getBusType().equals(dto.getBusType()) && bus.getBookings() != null && !bus.getBookings().isEmpty()) {

			throw new BusTypeModificationException("Cannot change bus type after bookings exist.");
		}

		Admin admin = adminRepo.findById(dto.getAdminId())
				.orElseThrow(() -> new AdminNotFoundException("Admin not found with id : " + dto.getAdminId()));

		Route route = routeRepo.findById(dto.getRouteId())
				.orElseThrow(() -> new RouteNotFoundException("Route not found with id : " + dto.getRouteId()));

		bus.setBusName(dto.getBusName());

		bus.setBusNumber(dto.getBusNumber());

		bus.setBusType(dto.getBusType());

		bus.setFare(dto.getFare());

		bus.setAdmin(admin);

		bus.setRoute(route);

		Bus updatedBus = busRepo.save(bus);

		return BusMapper.toResponseDTO(updatedBus);
	}

	@Override
	@Transactional
	public void deleteBus(Long id) {

		Bus bus = busRepo.findById(id).orElseThrow(() -> new BusNotFoundException("Bus not found with id : " + id));

		// Soft delete

		bus.setActive(false);

		busRepo.delete(bus);
	}

	@Override
	public List<BusResponseDTO> searchBuses(String source, String destination) {

		List<Bus> buses = busRepo.findByRouteSourceAndRouteDestinationAndActiveTrue(source, destination);
		if (buses.isEmpty()) {

			throw new NoBusFoundException("No buses available from " + source + " to " + destination);
		}

		return buses.stream().map(BusMapper::toResponseDTO).collect(Collectors.toList());
	}

	private void createSeats(Bus bus) {

		List<Seat> seats = new ArrayList<>();

		if (bus.getBusType() == BusType.AC_SEATER || bus.getBusType() == BusType.NON_AC_SEATER) {

			createSeaterSeats(bus, seats);

		}

		else {

			createSleeperSeats(bus, seats);
		}

		seatRepo.saveAll(seats);
	}

	private void createSeaterSeats(Bus bus, List<Seat> seats) {

		for (int i = 1; i <= bus.getTotalSeats(); i++) {

			Seat seat = new Seat();

			seat.setSeatNumber("A" + i);

			if (i % 4 == 1 || i % 4 == 0) {

				seat.setSeatType(SeatType.WINDOW);

			} else {

				seat.setSeatType(SeatType.AISLE);
			}

			seat.setSeatStatus(SeatStatus.AVAILABLE);

			seat.setBus(bus);

			seats.add(seat);
		}
	}

	private void createSleeperSeats(Bus bus, List<Seat> seats) {

		for (int i = 1; i <= bus.getTotalSeats(); i++) {

			Seat seat = new Seat();

			seat.setSeatNumber("S" + i);

			seat.setSeatType(SeatType.SLEEPER);

			seat.setSeatStatus(SeatStatus.AVAILABLE);

			seat.setBus(bus);

			seats.add(seat);
		}
	}

	@Override
	public List<Bus> findAvailableBuses(String source, String destination) {
		// TODO Auto-generated method stub
		Route route = routeService.findRoute(source, destination);

	    return busRepo.findByRouteAndActiveTrue(route);
	}

	@Override
	public List<BusSearchResponse> findAvailableBusesForAI(String source,
	                                                       String destination) {

	    Route route = routeService.findRoute(source, destination);

	    List<Bus> buses = busRepo.findByRouteAndActiveTrue(route);

	    if (buses.isEmpty()) {
	        throw new NoBusFoundException(
	                "No buses available from " + source + " to " + destination);
	    }

	    return buses.stream()
	            .map(bus -> new BusSearchResponse(
	                    bus.getBusId(),
	                    bus.getBusName(),
	                    bus.getBusNumber(),
	                    bus.getBusType().name(),
	                    bus.getFare(),
	                    bus.getAvailableSeats()
	            ))
	            .toList();
	}

}