package com.project.BusTicketBooking.dto.ai;

public class BusSearchResponse {

    private Long busId;
    private String busName;
    private String busNumber;
    private String busType;
    private Double fare;
    private Integer availableSeats;

    public BusSearchResponse() {
    }

    public BusSearchResponse(Long busId,
                             String busName,
                             String busNumber,
                             String busType,
                             Double fare,
                             Integer availableSeats) {

        this.busId = busId;
        this.busName = busName;
        this.busNumber = busNumber;
        this.busType = busType;
        this.fare = fare;
        this.availableSeats = availableSeats;
    }

    // getters setters
}