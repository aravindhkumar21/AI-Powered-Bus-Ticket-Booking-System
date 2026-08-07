package com.project.BusTicketBooking.dto.bus;

import com.project.BusTicketBooking.enums.BusType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BusRequestDTO {

    @NotBlank(message = "Bus name is required")
    private String busName;

    @NotBlank(message = "Bus number is required")
    private String busNumber;

    @NotNull(message = "Bus type is required")
    private BusType busType;

    @Positive(message = "Fare must be greater than 0")
    private Double fare;

    @Min(value = 1, message = "Total seats must be greater than 0")
    private Integer totalSeats;

    @NotNull(message = "Admin Id is required")
    private Long adminId;

    @NotNull(message = "Route Id is required")
    private Long routeId;

    public BusRequestDTO() {
    }

    public BusRequestDTO(String busName, String busNumber, BusType busType,
                         Double fare, Integer totalSeats,
                         Long adminId, Long routeId) {

        this.busName = busName;
        this.busNumber = busNumber;
        this.busType = busType;
        this.fare = fare;
        this.totalSeats = totalSeats;
        this.adminId = adminId;
        this.routeId = routeId;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

    // Generate Getters & Setters
    
}
