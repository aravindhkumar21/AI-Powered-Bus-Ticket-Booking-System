package com.project.BusTicketBooking.ai.tool;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.BusTicketBooking.dto.ai.BusSearchResponse;
import com.project.BusTicketBooking.service.BusService;

@Component
public class BusSearchTool {

    private final BusService busService;

    public BusSearchTool(BusService busService) {
        this.busService = busService;
    }

    public List<BusSearchResponse> search(
            String source,
            String destination) {

        return busService.findAvailableBusesForAI(
                source,
                destination
        );
    }

    public String format(
            List<BusSearchResponse> buses,
            String source,
            String destination) {

        StringBuilder response = new StringBuilder();

        response.append("🚌 Available buses from ")
                .append(source)
                .append(" to ")
                .append(destination)
                .append("\n\n");

        for (int i = 0; i < buses.size(); i++) {

            BusSearchResponse bus = buses.get(i);

            response.append(i + 1)
                    .append(". ")
                    .append(bus.getBusName())
                    .append("\n")
                    .append("Bus Number: ")
                    .append(bus.getBusNumber())
                    .append("\n")
                    .append("Type: ")
                    .append(bus.getBusType())
                    .append("\n")
                    .append("Fare: ₹")
                    .append(bus.getFare())
                    .append("\n")
                    .append("Available Seats: ")
                    .append(bus.getAvailableSeats())
                    .append("\n\n");
        }

        response.append(
                "Reply with the option number to continue."
        );

        return response.toString();
    }
}