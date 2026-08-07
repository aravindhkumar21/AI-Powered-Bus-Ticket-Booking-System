package com.project.BusTicketBooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.BusTicketBooking.enums.SeatStatus;
import com.project.BusTicketBooking.enums.SeatType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(
    name = "seats",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"seat_number","bus_id"}
        )
    }
)
public class Seat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;


    @NotBlank(message = "Seat number is required")
    private String seatNumber;


    @NotNull(message = "Seat type is required")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;


    @NotNull(message = "Seat status is required")
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;


    @ManyToOne
    @JoinColumn(name="bus_id")
    @JsonIgnore
    private Bus bus;


    @ManyToMany(mappedBy = "seats")
    @JsonIgnore
    private List<Booking> bookings;



    public Seat() {
    }


    public Seat(Long seatId, String seatNumber, SeatType seatType,
            SeatStatus seatStatus, Bus bus, List<Booking> bookings) {

        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.seatStatus = seatStatus;
        this.bus = bus;
        this.bookings = bookings;
    }



    public Long getSeatId() {
        return seatId;
    }


    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }


    public String getSeatNumber() {
        return seatNumber;
    }


    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }


    public SeatType getSeatType() {
        return seatType;
    }


    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }


    public SeatStatus getSeatStatus() {
        return seatStatus;
    }


    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }


    public Bus getBus() {
        return bus;
    }


    public void setBus(Bus bus) {
        this.bus = bus;
    }


    public List<Booking> getBookings() {
        return bookings;
    }


    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }



    @Override
    public String toString() {

        return "Seat{" +
                "seatId=" + seatId +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType=" + seatType +
                ", seatStatus=" + seatStatus +
                '}';
    }

}