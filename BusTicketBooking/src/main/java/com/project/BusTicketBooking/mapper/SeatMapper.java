package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;
import com.project.BusTicketBooking.model.Seat;

public class SeatMapper {

    public static SeatResponseDTO toResponseDTO(Seat seat){

        SeatResponseDTO dto = new SeatResponseDTO();

        dto.setSeatId(seat.getSeatId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setSeatType(seat.getSeatType());
        dto.setSeatStatus(seat.getSeatStatus());

        dto.setBus(BusMapper.toResponseDTO(seat.getBus()));

        return dto;
    }

}
