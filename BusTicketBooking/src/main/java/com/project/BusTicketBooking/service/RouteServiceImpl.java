package com.project.BusTicketBooking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.route.RouteRequestDTO;
import com.project.BusTicketBooking.dto.route.RouteResponseDTO;
import com.project.BusTicketBooking.exception.RouteNotFoundException;
import com.project.BusTicketBooking.mapper.RouteMapper;
import com.project.BusTicketBooking.model.Route;
import com.project.BusTicketBooking.repo.RouteRepository;

@Service
public class RouteServiceImpl implements RouteService {
	
	private final RouteRepository routeRepo;
	
	

	public RouteServiceImpl(RouteRepository routeRepo) {
		this.routeRepo = routeRepo;
	}

	@Override
	public RouteResponseDTO saveRoute(RouteRequestDTO dto) {
		Route route = RouteMapper.toEntity(dto);
		Route savedRoute = routeRepo.save(route);
		return RouteMapper.toResponseDTO(savedRoute);
	}

	@Override
	public List<RouteResponseDTO> getAllRoutes() {
		return routeRepo.findAll().stream().map(RouteMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public RouteResponseDTO getRouteById(Long id) {
		Route route = routeRepo.findById(id).orElseThrow(()->new RouteNotFoundException("Route id : "+id+" not found"));
		return RouteMapper.toResponseDTO(route);
	}

	@Override
	public RouteResponseDTO updateRoute(Long id, RouteRequestDTO dto) {
		Route existingRoute = routeRepo.findById(id)
                .orElseThrow(() ->
                        new RouteNotFoundException("Route not found with id : " + id));

        RouteMapper.updateEntity(existingRoute, dto);
        Route updatedRoute = routeRepo.save(existingRoute);
        return RouteMapper.toResponseDTO(updatedRoute);
	}

	@Override
	public void deleteRoute(Long id) {
		Route route = routeRepo.findById(id)
                .orElseThrow(() ->
                        new RouteNotFoundException("Route not found with id : " + id));

        routeRepo.delete(route);
		
	}

	@Override
	public Route findRoute(String source, String destination) {
		// TODO Auto-generated method stub
		return routeRepo
	            .findBySourceIgnoreCaseAndDestinationIgnoreCase(source, destination)
	            .orElseThrow(() ->
	                    new RuntimeException(
	                            "No route found from " + source + " to " + destination));
	}

}
