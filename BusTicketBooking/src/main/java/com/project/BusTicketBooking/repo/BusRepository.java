package com.project.BusTicketBooking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.model.Route;

public interface BusRepository extends JpaRepository<Bus, Long> {

	List<Bus> findByRouteSourceAndRouteDestinationAndActiveTrue(String source, String destination);
	List<Bus> findByRouteAndActiveTrue(Route route);

}