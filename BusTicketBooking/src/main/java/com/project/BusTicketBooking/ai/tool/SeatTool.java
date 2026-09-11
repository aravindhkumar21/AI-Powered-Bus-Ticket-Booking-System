package com.project.BusTicketBooking.ai.tool;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;
import com.project.BusTicketBooking.service.SeatService;

@Component
public class SeatTool {

    private final SeatService seatService;

    public SeatTool(SeatService seatService) {
        this.seatService = seatService;
    }

    public List<SeatResponseDTO> getSeats(Long busId) {

        return seatService.getSeatsByBusId(busId);
    }

    public List<SeatResponseDTO> getAvailableSeats(Long busId) {

        return seatService.getSeatsByBusId(busId)
                .stream()
                .filter(seat ->
                        "AVAILABLE".equalsIgnoreCase(
                                seat.getSeatStatus().name()
                        ))
                .collect(Collectors.toList());
    }

    public String format(
            List<SeatResponseDTO> seats) {

        if (seats.isEmpty()) {
            return "No seats are currently available.";
        }

        StringBuilder response =
                new StringBuilder("💺 Available seats:\n\n");

        for (SeatResponseDTO seat : seats) {

            response.append(seat.getSeatNumber())
                    .append(" - ")
                    .append(seat.getSeatType())
                    .append("\n");
        }

        response.append(
                "\nReply with seat number(s). Example: A1,A2"
        );

        return response.toString();
    }
}