package com.project.BusTicketBooking.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByBookingBookingId(Long bookingId);
    List<Payment> findByBookingUserUserId(Long userId);

}