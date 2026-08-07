package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.route.RouteRequestDTO;
import com.project.BusTicketBooking.dto.route.RouteResponseDTO;
import com.project.BusTicketBooking.model.Route;

public interface RouteService {
	
    RouteResponseDTO saveRoute(RouteRequestDTO dto);

    List<RouteResponseDTO> getAllRoutes();

    RouteResponseDTO getRouteById(Long id);

    RouteResponseDTO updateRoute(Long id, RouteRequestDTO dto);

    void deleteRoute(Long id);
    
    Route findRoute(String source, String destination);

}
