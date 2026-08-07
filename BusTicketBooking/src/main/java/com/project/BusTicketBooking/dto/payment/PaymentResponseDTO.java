package com.project.BusTicketBooking.dto.payment;

import java.time.LocalDateTime;

import com.project.BusTicketBooking.dto.booking.BookingResponseDTO;
import com.project.BusTicketBooking.enums.PaymentMethod;
import com.project.BusTicketBooking.enums.PaymentStatus;

public class PaymentResponseDTO {

	private Long paymentId;

	private Double amount;

	private PaymentMethod paymentMethod;

	private PaymentStatus paymentStatus;

	private LocalDateTime paymentTime;

	private BookingResponseDTO booking;

	public PaymentResponseDTO() {
	}

	public PaymentResponseDTO(Long paymentId, Double amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus,
			LocalDateTime paymentTime, BookingResponseDTO booking) {
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.paymentTime = paymentTime;
		this.booking = booking;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	public BookingResponseDTO getBooking() {
		return booking;
	}

	public void setBooking(BookingResponseDTO booking) {
		this.booking = booking;
	}
	
	@Override
	public String toString() {
	    return "PaymentResponseDTO{" +
	            "paymentId=" + paymentId +
	            ", amount=" + amount +
	            ", paymentMethod=" + paymentMethod +
	            ", paymentStatus=" + paymentStatus +
	            ", paymentTime=" + paymentTime +
	            '}';
	}
}
