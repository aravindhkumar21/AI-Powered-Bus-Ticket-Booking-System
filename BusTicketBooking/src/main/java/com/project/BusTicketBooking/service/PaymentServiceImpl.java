package com.project.BusTicketBooking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.payment.PaymentRequestDTO;
import com.project.BusTicketBooking.dto.payment.PaymentResponseDTO;
import com.project.BusTicketBooking.enums.PaymentStatus;
import com.project.BusTicketBooking.exception.BookingNotFoundException;
import com.project.BusTicketBooking.exception.PaymentAlreadyExistsException;
import com.project.BusTicketBooking.exception.PaymentNotFoundException;
import com.project.BusTicketBooking.mapper.PaymentMapper;
import com.project.BusTicketBooking.model.Booking;
import com.project.BusTicketBooking.model.Payment;
import com.project.BusTicketBooking.repo.BookingRepository;
import com.project.BusTicketBooking.repo.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepo;
	private final BookingRepository bookingRepo;

	public PaymentServiceImpl(PaymentRepository paymentRepo, BookingRepository bookingRepo) {

		this.paymentRepo = paymentRepo;
		this.bookingRepo = bookingRepo;
	}

	@Override
	@Transactional
	public PaymentResponseDTO savePayment(PaymentRequestDTO dto) {

		Booking booking = bookingRepo.findById(dto.getBookingId())
				.orElseThrow(() -> new BookingNotFoundException("Booking not found with id : " + dto.getBookingId()));

		// One payment per booking
		if (paymentRepo.findByBookingBookingId(dto.getBookingId()).isPresent()) {

			throw new PaymentAlreadyExistsException("Payment already exists for Booking ID : " + dto.getBookingId());
		}

		Payment payment = new Payment();

		// System generated values
		payment.setAmount(booking.getTotalAmount());
		payment.setPaymentMethod(dto.getPaymentMethod());
		payment.setPaymentStatus(PaymentStatus.SUCCESS);
		payment.setPaymentTime(LocalDateTime.now());
		payment.setBooking(booking);

		Payment savedPayment = paymentRepo.save(payment);

		return PaymentMapper.toResponseDTO(savedPayment);
	}

	@Override
	public List<PaymentResponseDTO> getAllPayments() {

		return paymentRepo.findAll().stream().map(PaymentMapper::toResponseDTO).collect(Collectors.toList());
	}
	
	@Override
	public List<PaymentResponseDTO> getPaymentsByUserId(Long userId) {

	    return paymentRepo.findByBookingUserUserId(userId)
	            .stream()
	            .map(PaymentMapper::toResponseDTO)
	            .collect(Collectors.toList());
	}

	@Override
	public PaymentResponseDTO getPaymentById(Long id) {

		Payment payment = paymentRepo.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id : " + id));

		return PaymentMapper.toResponseDTO(payment);
	}

	@Override
	@Transactional
	public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO dto) {

		Payment payment = paymentRepo.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id : " + id));

		/*
		 * Booking, Amount, Payment Status and Payment Time should never be modified
		 * after payment creation. Only Payment Method can be updated.
		 */

		payment.setPaymentMethod(dto.getPaymentMethod());

		Payment updatedPayment = paymentRepo.save(payment);

		return PaymentMapper.toResponseDTO(updatedPayment);
	}

	@Override
	@Transactional
	public void deletePayment(Long id) {

		Payment payment = paymentRepo.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id : " + id));

		paymentRepo.delete(payment);
	}
}