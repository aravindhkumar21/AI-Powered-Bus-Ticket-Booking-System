package com.project.BusTicketBooking.dto.booking;

import java.time.LocalDate;
import java.util.List;

import com.project.BusTicketBooking.dto.bus.BusResponseDTO;
import com.project.BusTicketBooking.dto.seat.SeatResponseDTO;
import com.project.BusTicketBooking.dto.user.UserResponseDTO;
import com.project.BusTicketBooking.enums.BookingStatus;

public class BookingResponseDTO {

    private Long bookingId;

    private LocalDate bookingDate;

    private LocalDate travelDate;

    private Integer numberOfSeats;

    private Double totalAmount;

    private BookingStatus bookingStatus;

    private UserResponseDTO user;

    private BusResponseDTO bus;

    private List<SeatResponseDTO> seats;

    public BookingResponseDTO() {
    }

	public BookingResponseDTO(Long bookingId, LocalDate bookingDate, LocalDate travelDate, Integer numberOfSeats,
			Double totalAmount, BookingStatus bookingStatus, UserResponseDTO user, BusResponseDTO bus,
			List<SeatResponseDTO> seats) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.travelDate = travelDate;
		this.numberOfSeats = numberOfSeats;
		this.totalAmount = totalAmount;
		this.bookingStatus = bookingStatus;
		this.user = user;
		this.bus = bus;
		this.seats = seats;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public Integer getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public UserResponseDTO getUser() {
		return user;
	}

	public void setUser(UserResponseDTO user) {
		this.user = user;
	}

	public BusResponseDTO getBus() {
		return bus;
	}

	public void setBus(BusResponseDTO bus) {
		this.bus = bus;
	}

	public List<SeatResponseDTO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatResponseDTO> seats) {
		this.seats = seats;
	}

    // Generate Constructor
    // Generate Getters & Setters
    
    
}
