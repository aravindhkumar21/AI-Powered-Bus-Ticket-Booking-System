package com.project.BusTicketBooking.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BusTicketBooking.enums.PaymentMethod;
import com.project.BusTicketBooking.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	// System generated from Booking totalAmount
	@Positive(message = "Amount must be greater than 0")
	private Double amount;

	@NotNull(message = "Payment method is required")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	@NotNull(message = "Payment status is required")
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	// System generated
	private LocalDateTime paymentTime;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booking_id", unique = true)
	@JsonIgnore
	private Booking booking;

	public Payment() {
	}

	public Payment(Long paymentId, Double amount, PaymentMethod paymentMethod,
			PaymentStatus paymentStatus, LocalDateTime paymentTime,
			Booking booking) {

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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@Override
	public String toString() {
		return "Payment{" +
				"paymentId=" + paymentId +
				", amount=" + amount +
				", paymentMethod=" + paymentMethod +
				", paymentStatus=" + paymentStatus +
				", paymentTime=" + paymentTime +
				'}';
	}
}