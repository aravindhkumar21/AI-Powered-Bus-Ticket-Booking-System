package com.project.BusTicketBooking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BusTicketBooking.enums.BusType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "buses")
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long busId;

	@NotBlank(message = "Bus name is required")
	private String busName;

	@NotBlank(message = "Bus number is required")
	private String busNumber;

	@Enumerated(EnumType.STRING)
	private BusType busType;

	@NotNull(message = "Fare is required")
	@Positive(message = "Fare must be greater than 0")
	private Double fare;

	@NotNull(message = "Total seats required")
	@Min(value = 1, message = "Total seats must be greater than 0")
	private Integer totalSeats;

	@Min(value = 0, message = "Available seats cannot be negative")
	private Integer availableSeats;

	// Soft delete status
	@Column(nullable = false)
	private Boolean active = true;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	// Automatically create and delete seats with bus
	@OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Seat> seats = new ArrayList<>();

	// Booking history should not be deleted
	@OneToMany(mappedBy = "bus")
	@JsonIgnore
	private List<Booking> bookings = new ArrayList<>();

	public Bus() {
	}

	public Bus(Long busId, String busName, String busNumber, BusType busType, Double fare, Integer totalSeats,
			Integer availableSeats, Boolean active, Admin admin, Route route, List<Seat> seats,
			List<Booking> bookings) {

		this.busId = busId;
		this.busName = busName;
		this.busNumber = busNumber;
		this.busType = busType;
		this.fare = fare;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.active = active;
		this.admin = admin;
		this.route = route;
		this.seats = seats;
		this.bookings = bookings;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {

		return "Bus{" + "busId=" + busId + ", busName='" + busName + '\'' + ", busNumber='" + busNumber + '\''
				+ ", busType=" + busType + ", fare=" + fare + ", totalSeats=" + totalSeats + ", availableSeats="
				+ availableSeats + ", active=" + active + '}';
	}
}