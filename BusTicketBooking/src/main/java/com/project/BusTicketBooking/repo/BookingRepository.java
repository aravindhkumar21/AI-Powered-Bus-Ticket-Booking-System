package com.project.BusTicketBooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	List<Booking> findByUserUserId(Long userId);
	List<Booking> findByBusBusId(Long busId);

}
