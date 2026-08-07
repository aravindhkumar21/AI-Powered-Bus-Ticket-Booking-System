package com.project.BusTicketBooking.dto.route;

import java.time.LocalTime;

public class RouteResponseDTO {

    private Long routeId;

    private String source;

    private String destination;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    public RouteResponseDTO() {
    }

    public RouteResponseDTO(Long routeId,
                            String source,
                            String destination,
                            LocalTime departureTime,
                            LocalTime arrivalTime) {

        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    
    // Getters & Setters

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

  
    
}