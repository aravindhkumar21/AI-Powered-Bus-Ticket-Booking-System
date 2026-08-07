package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.booking.BookingRequestDTO;
import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.dto.booking.BookingUpdateDTO;

public interface BookingService {

	BookingResponseDTO saveBooking(BookingRequestDTO dto);

	List<BookingResponseDTO> getAllBookings();

	BookingResponseDTO getBookingById(Long id);

	BookingResponseDTO updateBooking(Long id, BookingUpdateDTO dto);

	void deleteBooking(Long id);

}
