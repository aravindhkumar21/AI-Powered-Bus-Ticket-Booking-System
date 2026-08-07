package com.project.BusTicketBooking.ai.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.service.BusService;

@Service
public class BusSearchAiService {

    private final BusService busService;

    public BusSearchAiService(BusService busService) {
        this.busService = busService;
    }

    public String search(String source, String destination) {

        List<Bus> buses =
                busService.findAvailableBuses(source, destination);

        StringBuilder sb = new StringBuilder();

        sb.append("Available buses from ")
          .append(source)
          .append(" to ")
          .append(destination)
          .append("\n\n");

        int i = 1;

        for (Bus bus : buses) {

            sb.append(i++)
              .append(". ")
              .append(bus.getBusName())
              .append("\n")
              .append("Bus No : ")
              .append(bus.getBusNumber())
              .append("\n")
              .append("Type : ")
              .append(bus.getBusType())
              .append("\n")
              .append("Fare : ₹")
              .append(bus.getFare())
              .append("\n")
              .append("Available Seats : ")
              .append(bus.getAvailableSeats())
              .append("\n\n");
        }

        sb.append("Reply with the bus number to continue booking.");

        return sb.toString();
    }
}
