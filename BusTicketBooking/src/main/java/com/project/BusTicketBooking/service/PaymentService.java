package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.payment.PaymentRequestDTO;
import com.project.BusTicketBooking.dto.payment.PaymentResponseDTO;

public interface PaymentService {
	
	PaymentResponseDTO savePayment(PaymentRequestDTO dto);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO getPaymentById(Long id);

    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO dto);

    void deletePayment(Long id);

}
