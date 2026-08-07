package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.ai.BusSearchResponse;
import com.project.BusTicketBooking.dto.bus.BusRequestDTO;
import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.model.Bus;

public interface BusService {

	BusResponseDTO saveBus(BusRequestDTO dto);

	List<BusResponseDTO> getAllBuses();

	BusResponseDTO getBusById(Long id);

	BusResponseDTO updateBus(Long id, BusRequestDTO dto);

	void deleteBus(Long id);

	List<BusResponseDTO> searchBuses(String source, String destination);

	List<Bus> findAvailableBuses(String source, String destination);

	List<BusSearchResponse> findAvailableBusesForAI(String source, String destination);

}
