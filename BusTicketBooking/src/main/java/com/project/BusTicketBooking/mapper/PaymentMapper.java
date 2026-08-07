package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.payment.PaymentResponseDTO;
import com.project.BusTicketBooking.model.Payment;

public class PaymentMapper {

    private PaymentMapper() {
    }

    public static PaymentResponseDTO toResponseDTO(Payment payment) {

        if (payment == null) {
            return null;
        }

        PaymentResponseDTO dto = new PaymentResponseDTO();

        dto.setPaymentId(payment.getPaymentId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentStatus(payment.getPaymentStatus());
        dto.setPaymentTime(payment.getPaymentTime());

        dto.setBooking(
                BookingMapper.toResponseDTO(payment.getBooking())
        );

        return dto;
    }
}