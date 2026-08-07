package com.project.BusTicketBooking.dto.payment;

import com.project.BusTicketBooking.enums.PaymentMethod;

import jakarta.validation.constraints.NotNull;

public class PaymentRequestDTO {

    @NotNull(message = "Booking Id is required")
    private Long bookingId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    public PaymentRequestDTO() {
    }

    public PaymentRequestDTO(Long bookingId, PaymentMethod paymentMethod) {
        this.bookingId = bookingId;
        this.paymentMethod = paymentMethod;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}