package com.project.BusTicketBooking.dto.booking;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookingRequestDTO {

	@NotNull(message = "Travel date is required")
	private LocalDate travelDate;

	@NotNull(message = "User Id is required")
	private Long userId;

	@NotNull(message = "Bus Id is required")
	private Long busId;

	@NotEmpty(message = "Select at least one seat")
	private List<Long> seatIds;

	public BookingRequestDTO() {

	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public List<Long> getSeatIds() {
		return seatIds;
	}

	public void setSeatIds(List<Long> seatIds) {
		this.seatIds = seatIds;
	}
}