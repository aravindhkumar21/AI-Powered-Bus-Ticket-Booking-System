package com.project.BusTicketBooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
	Optional<Route> findBySourceIgnoreCaseAndDestinationIgnoreCase(
	        String source,
	        String destination);

}
