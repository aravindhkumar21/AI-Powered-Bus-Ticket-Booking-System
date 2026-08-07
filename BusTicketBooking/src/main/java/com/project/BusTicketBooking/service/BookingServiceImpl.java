package com.project.BusTicketBooking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.booking.BookingRequestDTO;
import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.dto.booking.BookingUpdateDTO;
import com.project.BusTicketBooking.enums.BookingStatus;
import com.project.BusTicketBooking.enums.SeatStatus;
import com.project.BusTicketBooking.exception.BookingNotFoundException;
import com.project.BusTicketBooking.exception.BusNotFoundException;
import com.project.BusTicketBooking.exception.SeatAlreadyBookedException;
import com.project.BusTicketBooking.exception.SeatNotBelongToBusException;
import com.project.BusTicketBooking.exception.SeatNotFoundException;
import com.project.BusTicketBooking.exception.UserNotFoundException;
import com.project.BusTicketBooking.mapper.BookingMapper;
import com.project.BusTicketBooking.model.Booking;
import com.project.BusTicketBooking.model.Bus;
import com.project.BusTicketBooking.model.Seat;
import com.project.BusTicketBooking.model.User;
import com.project.BusTicketBooking.repo.BookingRepository;
import com.project.BusTicketBooking.repo.BusRepository;
import com.project.BusTicketBooking.repo.SeatRepository;
import com.project.BusTicketBooking.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepo;
	private final UserRepository userRepo;
	private final BusRepository busRepo;
	private final SeatRepository seatRepo;

	public BookingServiceImpl(BookingRepository bookingRepo, UserRepository userRepo, BusRepository busRepo,
			SeatRepository seatRepo) {

		this.bookingRepo = bookingRepo;
		this.userRepo = userRepo;
		this.busRepo = busRepo;
		this.seatRepo = seatRepo;
	}

	@Override
	@Transactional
	public BookingResponseDTO saveBooking(BookingRequestDTO dto) {

		User user = userRepo.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found"));

		Bus bus = busRepo.findById(dto.getBusId()).orElseThrow(() -> new BusNotFoundException("Bus not found"));

		// Check active bus

		if (!bus.getActive()) {

			throw new RuntimeException("Bus is currently unavailable");
		}

		List<Seat> seats = seatRepo.findAllById(dto.getSeatIds());

		if (seats.size() != dto.getSeatIds().size()) {

			throw new SeatNotFoundException("Invalid seat selection");
		}

		for (Seat seat : seats) {

			if (!seat.getBus().getBusId().equals(bus.getBusId())) {

				throw new SeatNotBelongToBusException("Seat does not belong to this bus");

			}

			if (seat.getSeatStatus() == SeatStatus.BOOKED) {

				throw new SeatAlreadyBookedException(seat.getSeatNumber() + " already booked");
			}

			seat.setSeatStatus(SeatStatus.BOOKED);

		}

		seatRepo.saveAll(seats);

		bus.setAvailableSeats(bus.getAvailableSeats() - seats.size());

		busRepo.save(bus);

		Booking booking = new Booking();

		booking.setBookingDate(LocalDate.now());

		booking.setTravelDate(dto.getTravelDate());

		// System generated

		booking.setNumberOfSeats(seats.size());

		booking.setTotalAmount(bus.getFare() * seats.size());

		booking.setBookingStatus(BookingStatus.BOOKED);

		booking.setUser(user);

		booking.setBus(bus);

		booking.setSeats(seats);

		Booking saved = bookingRepo.save(booking);

		return BookingMapper.toResponseDTO(saved);

	}

	@Override
	public List<BookingResponseDTO> getAllBookings() {

		return bookingRepo.findAll().stream().map(BookingMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public BookingResponseDTO getBookingById(Long id) {

		Booking booking = bookingRepo.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with id : " + id));

		return BookingMapper.toResponseDTO(booking);
	}

	@Override
	@Transactional
	public BookingResponseDTO updateBooking(Long id, BookingUpdateDTO dto) {

		Booking booking = bookingRepo.findById(id)
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with id : " + id));

		// Only travel date can be updated

		booking.setTravelDate(dto.getTravelDate());

		Booking updatedBooking = bookingRepo.save(booking);

		return BookingMapper.toResponseDTO(updatedBooking);
	}

	@Override
	@Transactional
	public void deleteBooking(Long id){


	    Booking booking =
	        bookingRepo.findById(id)
	        .orElseThrow(
	        ()->new BookingNotFoundException(
	        "Booking not found")
	        );



	    // Cancel booking instead of deleting


	    booking.setBookingStatus(
	        BookingStatus.CANCELLED
	    );



	    List<Seat> seats =
	        booking.getSeats();



	    for(Seat seat: seats){

	        seat.setSeatStatus(
	        SeatStatus.AVAILABLE
	        );

	    }


	    seatRepo.saveAll(seats);



	    Bus bus =
	        booking.getBus();



	    bus.setAvailableSeats(
	        bus.getAvailableSeats()+seats.size()
	    );


	    busRepo.save(bus);



	    bookingRepo.save(booking);

	}

}