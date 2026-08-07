package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.model.Bus;

public class BusMapper {

    // Entity -> ResponseDTO
    public static BusResponseDTO toResponseDTO(Bus bus) {

        BusResponseDTO dto = new BusResponseDTO();

        dto.setBusId(bus.getBusId());
        dto.setBusName(bus.getBusName());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setActive(bus.getActive());
        dto.setFare(bus.getFare());
        dto.setTotalSeats(bus.getTotalSeats());
        dto.setAvailableSeats(bus.getAvailableSeats());



        // Nested DTO Mapping
        dto.setAdmin(
                AdminMapper.toResponseDTO(bus.getAdmin())
        );

        dto.setRoute(
                RouteMapper.toResponseDTO(bus.getRoute())
        );


        return dto;
    }

}