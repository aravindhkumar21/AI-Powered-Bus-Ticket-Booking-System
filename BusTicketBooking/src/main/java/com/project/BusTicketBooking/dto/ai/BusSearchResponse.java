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

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

    // getters setters
    
}
