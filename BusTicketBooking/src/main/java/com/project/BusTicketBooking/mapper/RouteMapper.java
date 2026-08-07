package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.route.RouteRequestDTO;
import com.project.BusTicketBooking.dto.route.RouteResponseDTO;
import com.project.BusTicketBooking.model.Route;

public class RouteMapper {

    public static Route toEntity(RouteRequestDTO dto){

        Route route = new Route();

        route.setSource(dto.getSource());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());

        return route;
    }

    public static RouteResponseDTO toResponseDTO(Route route){

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setRouteId(route.getRouteId());
        dto.setSource(route.getSource());
        dto.setDestination(route.getDestination());
        dto.setDepartureTime(route.getDepartureTime());
        dto.setArrivalTime(route.getArrivalTime());

        return dto;
    }

    public static void updateEntity(Route route,
                                    RouteRequestDTO dto){

        route.setSource(dto.getSource());
        route.setDestination(dto.getDestination());
        route.setDepartureTime(dto.getDepartureTime());
        route.setArrivalTime(dto.getArrivalTime());
    }
}
