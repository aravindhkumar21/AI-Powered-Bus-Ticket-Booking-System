package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.seat.SeatRequestDTO;
import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;

public interface SeatService {
	List<SeatResponseDTO> getSeatsByBusId(Long busId);

    List<SeatResponseDTO> getAllSeats();

    SeatResponseDTO getSeatById(Long id);

    SeatResponseDTO updateSeat(Long id, SeatRequestDTO dto);

    void deleteSeat(Long id);

}
