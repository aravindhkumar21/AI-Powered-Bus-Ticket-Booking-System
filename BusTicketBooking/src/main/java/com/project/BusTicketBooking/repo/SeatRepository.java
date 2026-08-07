package com.project.BusTicketBooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {

	List<Seat> findByBusBusIdAndBusActiveTrue(Long busId);

}