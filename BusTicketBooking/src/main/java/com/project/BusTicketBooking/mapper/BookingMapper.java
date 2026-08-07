package com.project.BusTicketBooking.mapper;

import java.util.Collections;
import java.util.stream.Collectors;

import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.model.Booking;

public class BookingMapper {

	public static BookingResponseDTO toResponseDTO(Booking booking) {

		BookingResponseDTO dto = new BookingResponseDTO();

		dto.setBookingId(booking.getBookingId());

		dto.setBookingDate(booking.getBookingDate());

		dto.setTravelDate(booking.getTravelDate());

		dto.setNumberOfSeats(booking.getNumberOfSeats());

		dto.setTotalAmount(booking.getTotalAmount());

		dto.setBookingStatus(booking.getBookingStatus());

		if (booking.getUser() != null) {

			dto.setUser(UserMapper.toResponseDTO(booking.getUser()));
		}

		if (booking.getBus() != null) {

			dto.setBus(BusMapper.toResponseDTO(booking.getBus()));
		}

		dto.setSeats(

				booking.getSeats() == null ? Collections.emptyList()
						: booking.getSeats().stream().map(SeatMapper::toResponseDTO).collect(Collectors.toList())

		);

		return dto;
	}

}