package com.project.BusTicketBooking.dto.bus;

import com.project.BusTicketBooking.dto.admin.AdminResponseDTO;
import com.project.BusTicketBooking.dto.route.RouteResponseDTO;
import com.project.BusTicketBooking.enums.BusType;

public class BusResponseDTO {

    private Long busId;

    private String busName;

    private String busNumber;

    private BusType busType;
    
    private Boolean active;

    private Double fare;

    private Integer totalSeats;

    private Integer availableSeats;

    private AdminResponseDTO admin;

    private RouteResponseDTO route;

    public BusResponseDTO() {
    }

    public BusResponseDTO(Long busId, String busName, String busNumber,
                          BusType busType,Boolean active, Double fare,
                          Integer totalSeats, Integer availableSeats,
                          AdminResponseDTO admin,
                          RouteResponseDTO route) {

        this.busId = busId;
        this.busName = busName;
        this.busNumber = busNumber;
        this.busType = busType;
        this.active=active;
        this.fare = fare;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.admin = admin;
        this.route = route;
    }

    
    
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public AdminResponseDTO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminResponseDTO admin) {
		this.admin = admin;
	}

	public RouteResponseDTO getRoute() {
		return route;
	}

	public void setRoute(RouteResponseDTO route) {
		this.route = route;
	}

    // Generate Getters & Setters
    
}
