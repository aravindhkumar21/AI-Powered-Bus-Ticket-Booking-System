package com.project.BusTicketBooking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.seat.SeatRequestDTO;
import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;
import com.project.BusTicketBooking.exception.BusNotFoundException;
import com.project.BusTicketBooking.exception.SeatNotFoundException;
import com.project.BusTicketBooking.mapper.SeatMapper;
import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.model.Seat;
import com.project.BusTicketBooking.repo.BusRepository;
import com.project.BusTicketBooking.repo.SeatRepository;

@Service
public class SeatServiceImpl implements SeatService {

	private final SeatRepository seatRepo;

	private final BusRepository busRepo;

	public SeatServiceImpl(SeatRepository seatRepo, BusRepository busRepo) {

		this.seatRepo = seatRepo;
		this.busRepo = busRepo;
	}

	@Override
	public List<SeatResponseDTO> getSeatsByBusId(Long busId) {

		return seatRepo.findByBusBusIdAndBusActiveTrue(busId).stream().map(SeatMapper::toResponseDTO)
				.collect(Collectors.toList());

	}

	@Override
	public List<SeatResponseDTO> getAllSeats() {

		return seatRepo.findAll().stream().map(SeatMapper::toResponseDTO).collect(Collectors.toList());

	}

	@Override
	public SeatResponseDTO getSeatById(Long id) {

		Seat seat = seatRepo.findById(id)
				.orElseThrow(() -> new SeatNotFoundException("Seat not found with id : " + id));

		return SeatMapper.toResponseDTO(seat);

	}

	@Override
	public SeatResponseDTO updateSeat(Long id, SeatRequestDTO dto) {

		Seat seat = seatRepo.findById(id)
				.orElseThrow(() -> new SeatNotFoundException("Seat not found with id : " + id));

		Bus bus = busRepo.findById(dto.getBusId())
				.orElseThrow(() -> new BusNotFoundException("Bus not found with id : " + dto.getBusId()));

		seat.setSeatNumber(dto.getSeatNumber());

		seat.setSeatType(dto.getSeatType());

		// DO NOT UPDATE STATUS HERE

		seat.setBus(bus);

		Seat updatedSeat = seatRepo.save(seat);

		return SeatMapper.toResponseDTO(updatedSeat);

	}

	@Override
	public void deleteSeat(Long id) {

		Seat seat = seatRepo.findById(id)
				.orElseThrow(() -> new SeatNotFoundException("Seat not found with id : " + id));

		seatRepo.delete(seat);

	}

}